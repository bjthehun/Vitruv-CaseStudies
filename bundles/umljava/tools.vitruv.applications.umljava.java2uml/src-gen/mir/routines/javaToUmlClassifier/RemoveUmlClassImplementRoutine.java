package mir.routines.javaToUmlClassifier;

import java.io.IOException;
import mir.routines.javaToUmlClassifier.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.Interface;
import tools.vitruv.applications.umljava.java2uml.JavaToUmlHelper;
import tools.vitruv.applications.umljava.util.uml.UmlClassifierAndPackageUtil;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class RemoveUmlClassImplementRoutine extends AbstractRepairRoutineRealization {
  private RoutinesFacade actionsFacade;
  
  private RemoveUmlClassImplementRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getElement1(final org.emftext.language.java.classifiers.Class jClass, final Interface jInterface, final org.eclipse.uml2.uml.Class uClass) {
      return uClass;
    }
    
    public EObject getCorrepondenceSourceUClass(final org.emftext.language.java.classifiers.Class jClass, final Interface jInterface) {
      return jClass;
    }
    
    public void update0Element(final org.emftext.language.java.classifiers.Class jClass, final Interface jInterface, final org.eclipse.uml2.uml.Class uClass) {
      UmlClassifierAndPackageUtil.removeUmlImplementedInterface(uClass, 
        JavaToUmlHelper.<org.eclipse.uml2.uml.Interface>findFirstCorrespondeningUmlElementByNameAndType(this.correspondenceModel, 
          jInterface.getName(), org.eclipse.uml2.uml.Interface.class));
    }
  }
  
  public RemoveUmlClassImplementRoutine(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final org.emftext.language.java.classifiers.Class jClass, final Interface jInterface) {
    super(reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.javaToUmlClassifier.RemoveUmlClassImplementRoutine.ActionUserExecution(getExecutionState(), this);
    this.actionsFacade = new mir.routines.javaToUmlClassifier.RoutinesFacade(getExecutionState(), this);
    this.jClass = jClass;this.jInterface = jInterface;
  }
  
  private org.emftext.language.java.classifiers.Class jClass;
  
  private Interface jInterface;
  
  protected void executeRoutine() throws IOException {
    getLogger().debug("Called routine RemoveUmlClassImplementRoutine with input:");
    getLogger().debug("   Class: " + this.jClass);
    getLogger().debug("   Interface: " + this.jInterface);
    
    org.eclipse.uml2.uml.Class uClass = getCorrespondingElement(
    	userExecution.getCorrepondenceSourceUClass(jClass, jInterface), // correspondence source supplier
    	org.eclipse.uml2.uml.Class.class,
    	(org.eclipse.uml2.uml.Class _element) -> true, // correspondence precondition checker
    	null);
    if (uClass == null) {
    	return;
    }
    registerObjectUnderModification(uClass);
    // val updatedElement userExecution.getElement1(jClass, jInterface, uClass);
    userExecution.update0Element(jClass, jInterface, uClass);
    
    postprocessElements();
  }
}
