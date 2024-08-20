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
import org.junit.jupiter.params.provider.ValueSource
import tools.vitruv.applications.umljava.tests.uml2java.AbstractUmlToJavaTest
import tools.vitruv.change.atomic.uuid.Uuid
import tools.vitruv.change.composite.description.PropagatedChange
import tools.vitruv.change.composite.description.VitruviusChange
import tools.vitruv.change.composite.propagation.ChangePropagationListener

import static extension edu.kit.ipd.sdq.commons.util.org.eclipse.emf.ecore.resource.ResourceUtil.getFirstRootEObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterAll
import java.io.File

class JavaToUmlConstructionPerformanceTest extends AbstractUmlToJavaTest {
	static val RESOURCES_FOLDER = "resources/"
	static val RESULTS_FOLDER = "results/"
	static val timeRecorder = new ConsistencyPreservationTimeRecorder
	
	override void setup() {
		super.setup
	}

	@BeforeEach
	def void setup2() {
		this.viewFactory.autoClose = false
		this.virtualModel.addChangePropagationListener(timeRecorder)
	}
	
	@ParameterizedTest
	@ValueSource(strings=#[
		"mediastore/MediaStore_1_Packages",
		"mediastore/MediaStore_2_ClassAndInterfaceStubs",
		"mediastore/MediaStore_3_CompositeDataTypes",
		"mediastore/MediaStore_4_OperationSignatures",
		"mediastore/MediaStore_5_ProvidedRoles",
		"mediastore/MediaStore_NoSEFF",
		"synthetic/model1",
		"synthetic/model2",
		"suresh519/uml/MyProject", // UML model from "myproject" by suresh519: https://repository.genmymodel.com/suresh519/MyProject (12.5.2017)
		"orhanobut/uml/model" // UML model from the logger project by orhan obut:  https://github.com/orhanobut/logger (12.5.2017)
	])
	def void measurePerformance(String modelFileName) {
		transformUmlModelAndValidateJavaCode(RESOURCES_FOLDER,  modelFileName + "." + MODEL_FILE_EXTENSION)
	}
	
	def void transformUmlModelAndValidateJavaCode(String modelPath, String modelName) {
		val resourceSet = new ResourceSetImpl()
		val resource = resourceSet.getResource(URI.createFileURI(modelPath + modelName), true)
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
 *  <li>number of atomic/basic changes</li>
 * 	<li>start time (when did change propagation start?)</li>
 *	<li>end time (when did it end?)</li>
 * </ol>
 * 
 * @author Benedikt Jutz
 */
class ConsistencyPreservationTimeRecorder implements ChangePropagationListener {
	val List<Quadruple<Long, Integer, Long, Long>> timestamps = new LinkedList; 
	
	var long step = 0;
	var long startTime;
	var VitruviusChange<Uuid> change;

	def clear() {
		timestamps.clear
	} 
	
	override finishedChangePropagation(Iterable<PropagatedChange> propagatedChanges) {
		val timeNow = System.nanoTime
		timestamps.add(new Quadruple(step, change.EChanges.size, startTime, timeNow))
		step++
	}
	
	override startedChangePropagation(VitruviusChange<Uuid> changeToPropagate) {
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
		file.write('edit, steps, start, end')
		timestamps.iterator.forEach[it |
			file.newLine
			file.write('''«it.get0», «it.get1», «it.get2», «it.get3»''')
		]
		file.close
	}
}