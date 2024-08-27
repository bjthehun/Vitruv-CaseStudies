package tools.vitruv.applications.umljava.tests.performancetests

import edu.kit.ipd.sdq.commons.util.java.Quadruple
import java.io.BufferedWriter
import java.io.FileWriter
import java.util.LinkedList
import java.util.List
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.uml2.uml.Model
import org.junit.jupiter.params.ParameterizedTest
import tools.vitruv.applications.umljava.tests.uml2java.AbstractUmlToJavaTest
import tools.vitruv.change.atomic.uuid.Uuid
import tools.vitruv.change.composite.description.PropagatedChange
import tools.vitruv.change.composite.description.VitruviusChange
import tools.vitruv.change.composite.propagation.ChangePropagationListener

import static extension edu.kit.ipd.sdq.commons.util.org.eclipse.emf.ecore.resource.ResourceUtil.getFirstRootEObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterAll
import java.io.File
import java.util.stream.Stream
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import edu.kit.ipd.sdq.commons.util.java.Quintuple

class JavaToUmlConstructionPerformanceTest extends AbstractUmlToJavaTest {
	/**
	 * How many warm-up rounds?
	 */
	static val WARM_UP_ROUNDS = 10
	/**
	 * How many measurement rounds?
	 * Change as required
	 */
	static val MEASUREMENT_ROUNDS = 382
	
	/**
	 * UML files that we reconstruct; paths relatives to {@link RESOURCES_FOLDER}
	 */
	static val UMLFiles = newArrayList(
		"mediastore/MediaStore_1_Packages",
		"mediastore/MediaStore_2_ClassAndInterfaceStubs",
		"mediastore/MediaStore_3_CompositeDataTypes",
		"mediastore/MediaStore_4_OperationSignatures",
		"mediastore/MediaStore_5_ProvidedRoles",
		"mediastore/MediaStore_NoSEFF",
		"mediastore/ms_repository_noSEFF_unedited",
		"mediastore/ms_repository_reduced",
		"synthetic/model1",
		"synthetic/model2",
		"suresh519/uml/MyProject", // UML model from "myproject" by suresh519: https://repository.genmymodel.com/suresh519/MyProject (12.5.2017)
		"orhanobut/uml/model" // UML model from the logger project by orhan obut:  https://github.com/orhanobut/logger (12.5.2017)
	)
	/**
	 * Path for UML models
	 */
	static val RESOURCES_FOLDER = "resources/"
	/**
	 * Path to record results.
	 */
	static val RESULTS_FOLDER = "results/"
	/**
	 * Time recorder.
	 */
	static val timeRecorder = new ConsistencyPreservationTimeRecorder
	
	override void setup() {
		super.setup
	}

	/**
	 * Additional setup to install the time recorder.
	 */
	@BeforeEach
	def void setup2() {
		this.viewFactory.autoClose = false
		this.virtualModel.addChangePropagationListener(timeRecorder)
	}

	/**
	 * Creates an argument stream for performance tests:
	 * 
	 * <ol>
	 * 	<li>Return all UMLFiles for warm-up phase.</li>
	 * 	<li>Return them again for measurement phase.</li>
	 * </ol>
	 */
	static def Stream<Arguments> argumentsForPerformanceTest() {
		val warmUpList = UMLFiles.map[it | Arguments.of(it, false)]
		var warmUpStream = Stream.empty

		for (var i = 0; i < WARM_UP_ROUNDS; i++) {
			warmUpStream = Stream.concat(warmUpStream, warmUpList.stream)
		}
		
		val measureList = UMLFiles.map[it | Arguments.of(it, true)]
		var measureStream = Stream.empty
		for (var i = 0; i < MEASUREMENT_ROUNDS; i++) {
			measureStream = Stream.concat(measureStream, measureList.stream)
		}
				
		Stream.concat(warmUpStream, measureStream)
	}

	@ParameterizedTest
	@MethodSource("argumentsForPerformanceTest")
	def void measurePerformance(String modelFileName, boolean measure) {
		timeRecorder.measure = measure
		timeRecorder.name = modelFileName
		
		val resourceSet = new ResourceSetImpl()
		val resource = resourceSet.getResource(URI.createFileURI(RESOURCES_FOLDER + modelFileName + ".uml"), true)
		
		val model = resource.firstRootEObject as Model => [
			name = UML_MODEL_NAME
		]
		EcoreUtil.resolveAll(model)
		changeJavaView [
			createAndRegisterRoot(model, UML_MODEL_NAME.projectModelPath.uri)
		]
		resourceSet.resources.forEach[unload()]
		resourceSet.resources.clear()
	}
	
	@AfterAll
	static def void printStatistics() {
		val file = new File(RESULTS_FOLDER) 
		file.mkdir
		timeRecorder.printStatisticsAsCSV(RESULTS_FOLDER + "statistics.csv")
	}
}

/**
 * An implementation of ChangePropagationListener that takes note of changes to propagate and records:
 * 
 * <ol>
 * 	<li>a continuing number</li>
 * 	<li>the model name</li>
 *  <li>number of atomic/basic changes</li>
 * 	<li>start time (when did change propagation start?)</li>
 *	<li>end time (when did it end?)</li>
 * </ol>
 * 
 * @author Benedikt Jutz
 */
class ConsistencyPreservationTimeRecorder implements ChangePropagationListener {
	/**
	 * Records
	 */
	val List<Quintuple<Long, String, Integer, Long, Long>> timestamps = new LinkedList; 
	
	var long step = 0;
	var long startTime;
	var VitruviusChange<Uuid> change;
	var String name = '';
	
	/**
	 * Are we in the measurement phase, or warm-up phase (measure = false)?
	 * In the latter case, do not record measurements.
	 */
	boolean measure

	def setMeasure(boolean measure) {
		this.measure = measure
	}

	def setName(String name) {
		this.name = name;
	}
	
	override finishedChangePropagation(Iterable<PropagatedChange> propagatedChanges) {
		val timeNow = System.nanoTime
		if (!measure) {
			return
		}
		timestamps.add(new Quintuple(step, name, change.EChanges.size, startTime, timeNow))
		step++
	}
	
	override startedChangePropagation(VitruviusChange<Uuid> changeToPropagate) {
		if (!measure) {
			return
		}
		startTime = System.nanoTime
		change = changeToPropagate
	}

	/**
	 * Prints the recorded statistics per change to a CSV file located at path, relative to the current directory.
	 * 
	 * @param path - string
	 */
	def void printStatisticsAsCSV(String path) {
		val file = new BufferedWriter(new FileWriter(path))
		file.write('num, name, deltas, start, end')
		timestamps.iterator.forEach[it |
			file.newLine
			file.write('''«it.get0», «it.get1», «it.get2», «it.get3», «it.get4»''')
		]
		file.close
	}
}