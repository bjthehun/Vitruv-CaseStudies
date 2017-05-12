package mir.routines.javaToUmlClassifier;

import java.io.IOException;
import mir.routines.javaToUmlClassifier.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.Interface;
import tools.vitruv.applications.umljava.java2uml.JavaToUmlHelper;
import tools.vitruv.applications.umljava.util.uml.UmlClassifierAndPackageUtil;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class RemoveUmlSuperInterfaceRoutine extends AbstractRepairRoutineRealization {
  private RoutinesFacade actionsFacade;
  
  private RemoveUmlSuperInterfaceRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getElement1(final Interface jI, final Classifier jSuperClassifier, final org.eclipse.uml2.uml.Interface uInterface) {
      return uInterface;
    }
    
    public void update0Element(final Interface jI, final Classifier jSuperClassifier, final org.eclipse.uml2.uml.Interface uInterface) {
      UmlClassifierAndPackageUtil.removeUmlGeneralClassifier(uInterface, 
        JavaToUmlHelper.<org.eclipse.uml2.uml.Interface>findFirstCorrespondeningUmlElementByNameAndType(this.correspondenceModel, 
          jSuperClassifier.getName(), org.eclipse.uml2.uml.Interface.class));
    }
    
    public EObject getCorrepondenceSourceUInterface(final Interface jI, final Classifier jSuperClassifier) {
      return jI;
    }
  }
  
  public RemoveUmlSuperInterfaceRoutine(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final Interface jI, final Classifier jSuperClassifier) {
    super(reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.javaToUmlClassifier.RemoveUmlSuperInterfaceRoutine.ActionUserExecution(getExecutionState(), this);
    this.actionsFacade = new mir.routines.javaToUmlClassifier.RoutinesFacade(getExecutionState(), this);
    this.jI = jI;this.jSuperClassifier = jSuperClassifier;
  }
  
  private Interface jI;
  
  private Classifier jSuperClassifier;
  
  protected void executeRoutine() throws IOException {
    getLogger().debug("Called routine RemoveUmlSuperInterfaceRoutine with input:");
    getLogger().debug("   Interface: " + this.jI);
    getLogger().debug("   Classifier: " + this.jSuperClassifier);
    
    org.eclipse.uml2.uml.Interface uInterface = getCorrespondingElement(
    	userExecution.getCorrepondenceSourceUInterface(jI, jSuperClassifier), // correspondence source supplier
    	org.eclipse.uml2.uml.Interface.class,
    	(org.eclipse.uml2.uml.Interface _element) -> true, // correspondence precondition checker
    	null);
    if (uInterface == null) {
    	return;
    }
    registerObjectUnderModification(uInterface);
    // val updatedElement userExecution.getElement1(jI, jSuperClassifier, uInterface);
    userExecution.update0Element(jI, jSuperClassifier, uInterface);
    
    postprocessElements();
  }
}
