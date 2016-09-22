package mir.routines.pcm2java;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.InterfaceMethod;
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.TypeReference;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.ComposedStructure;
import org.palladiosimulator.pcm.core.entity.InterfaceRequiringEntity;
import org.palladiosimulator.pcm.core.entity.NamedElement;
import org.palladiosimulator.pcm.repository.CollectionDataType;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.InnerDeclaration;
import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.seff.ResourceDemandingInternalBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import tools.vitruv.extensions.dslsruntime.response.AbstractEffectsFacade;
import tools.vitruv.extensions.dslsruntime.response.ResponseExecutionState;
import tools.vitruv.extensions.dslsruntime.response.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class RoutinesFacade extends AbstractEffectsFacade {
  public RoutinesFacade(final ResponseExecutionState responseExecutionState, final CallHierarchyHaving calledBy) {
    super(responseExecutionState, calledBy);
  }
  
  public void callCreateRepositorySubPackages(final Repository repository) {
    mir.routines.pcm2java.CreateRepositorySubPackagesEffect effect = new mir.routines.pcm2java.CreateRepositorySubPackagesEffect(this.executionState, calledBy,
    	repository);
    effect.applyRoutine();
  }
  
  public void callRenamePackageForRepository(final Repository repository) {
    mir.routines.pcm2java.RenamePackageForRepositoryEffect effect = new mir.routines.pcm2java.RenamePackageForRepositoryEffect(this.executionState, calledBy,
    	repository);
    effect.applyRoutine();
  }
  
  public void callCreateImplementationForSystem(final org.palladiosimulator.pcm.system.System system) {
    mir.routines.pcm2java.CreateImplementationForSystemEffect effect = new mir.routines.pcm2java.CreateImplementationForSystemEffect(this.executionState, calledBy,
    	system);
    effect.applyRoutine();
  }
  
  public void callChangeSystemImplementationName(final org.palladiosimulator.pcm.system.System system) {
    mir.routines.pcm2java.ChangeSystemImplementationNameEffect effect = new mir.routines.pcm2java.ChangeSystemImplementationNameEffect(this.executionState, calledBy,
    	system);
    effect.applyRoutine();
  }
  
  public void callAddAssemblyContextToComposedStructure(final ComposedStructure composedStructure, final AssemblyContext assemblyContext) {
    mir.routines.pcm2java.AddAssemblyContextToComposedStructureEffect effect = new mir.routines.pcm2java.AddAssemblyContextToComposedStructureEffect(this.executionState, calledBy,
    	composedStructure, assemblyContext);
    effect.applyRoutine();
  }
  
  public void callCreateComponentImplementation(final RepositoryComponent component) {
    mir.routines.pcm2java.CreateComponentImplementationEffect effect = new mir.routines.pcm2java.CreateComponentImplementationEffect(this.executionState, calledBy,
    	component);
    effect.applyRoutine();
  }
  
  public void callCreateImplementationForComponent(final RepositoryComponent component) {
    mir.routines.pcm2java.CreateImplementationForComponentEffect effect = new mir.routines.pcm2java.CreateImplementationForComponentEffect(this.executionState, calledBy,
    	component);
    effect.applyRoutine();
  }
  
  public void callRenameComponentPackageAndClass(final RepositoryComponent component) {
    mir.routines.pcm2java.RenameComponentPackageAndClassEffect effect = new mir.routines.pcm2java.RenameComponentPackageAndClassEffect(this.executionState, calledBy,
    	component);
    effect.applyRoutine();
  }
  
  public void callRenameComponentClass(final RepositoryComponent component) {
    mir.routines.pcm2java.RenameComponentClassEffect effect = new mir.routines.pcm2java.RenameComponentClassEffect(this.executionState, calledBy,
    	component);
    effect.applyRoutine();
  }
  
  public void callCreateInterfaceImplementation(final Interface interf) {
    mir.routines.pcm2java.CreateInterfaceImplementationEffect effect = new mir.routines.pcm2java.CreateInterfaceImplementationEffect(this.executionState, calledBy,
    	interf);
    effect.applyRoutine();
  }
  
  public void callRenameInterface(final OperationInterface interf) {
    mir.routines.pcm2java.RenameInterfaceEffect effect = new mir.routines.pcm2java.RenameInterfaceEffect(this.executionState, calledBy,
    	interf);
    effect.applyRoutine();
  }
  
  public void callCreateCompositeDataTypeImplementation(final CompositeDataType dataType) {
    mir.routines.pcm2java.CreateCompositeDataTypeImplementationEffect effect = new mir.routines.pcm2java.CreateCompositeDataTypeImplementationEffect(this.executionState, calledBy,
    	dataType);
    effect.applyRoutine();
  }
  
  public void callRenameCompositeDataType(final CompositeDataType compositeDataType) {
    mir.routines.pcm2java.RenameCompositeDataTypeEffect effect = new mir.routines.pcm2java.RenameCompositeDataTypeEffect(this.executionState, calledBy,
    	compositeDataType);
    effect.applyRoutine();
  }
  
  public void callCreateCollectionDataTypeImplementation(final CollectionDataType dataType) {
    mir.routines.pcm2java.CreateCollectionDataTypeImplementationEffect effect = new mir.routines.pcm2java.CreateCollectionDataTypeImplementationEffect(this.executionState, calledBy,
    	dataType);
    effect.applyRoutine();
  }
  
  public void callAddSuperTypeToDataType(final DataType dataType, final TypeReference innerTypeReference, final String superTypeQualifiedName) {
    mir.routines.pcm2java.AddSuperTypeToDataTypeEffect effect = new mir.routines.pcm2java.AddSuperTypeToDataTypeEffect(this.executionState, calledBy,
    	dataType, innerTypeReference, superTypeQualifiedName);
    effect.applyRoutine();
  }
  
  public void callRenameCollectionDataType(final CollectionDataType collectionDataType) {
    mir.routines.pcm2java.RenameCollectionDataTypeEffect effect = new mir.routines.pcm2java.RenameCollectionDataTypeEffect(this.executionState, calledBy,
    	collectionDataType);
    effect.applyRoutine();
  }
  
  public void callCreateInnerDeclarationImplementation(final InnerDeclaration innerDeclaration) {
    mir.routines.pcm2java.CreateInnerDeclarationImplementationEffect effect = new mir.routines.pcm2java.CreateInnerDeclarationImplementationEffect(this.executionState, calledBy,
    	innerDeclaration);
    effect.applyRoutine();
  }
  
  public void callAddInnerDeclarationToCompositeDataType(final CompositeDataType dataType, final InnerDeclaration innerDeclaration, final TypeReference dataTypeReference) {
    mir.routines.pcm2java.AddInnerDeclarationToCompositeDataTypeEffect effect = new mir.routines.pcm2java.AddInnerDeclarationToCompositeDataTypeEffect(this.executionState, calledBy,
    	dataType, innerDeclaration, dataTypeReference);
    effect.applyRoutine();
  }
  
  public void callRenameInnerDeclarationImplementation(final InnerDeclaration innerDeclaration) {
    mir.routines.pcm2java.RenameInnerDeclarationImplementationEffect effect = new mir.routines.pcm2java.RenameInnerDeclarationImplementationEffect(this.executionState, calledBy,
    	innerDeclaration);
    effect.applyRoutine();
  }
  
  public void callChangeTypeOfInnerDeclarationImplementation(final InnerDeclaration innerDeclaration) {
    mir.routines.pcm2java.ChangeTypeOfInnerDeclarationImplementationEffect effect = new mir.routines.pcm2java.ChangeTypeOfInnerDeclarationImplementationEffect(this.executionState, calledBy,
    	innerDeclaration);
    effect.applyRoutine();
  }
  
  public void callChangeInnerDeclarationType(final InnerDeclaration innerDeclaration, final TypeReference newTypeReference) {
    mir.routines.pcm2java.ChangeInnerDeclarationTypeEffect effect = new mir.routines.pcm2java.ChangeInnerDeclarationTypeEffect(this.executionState, calledBy,
    	innerDeclaration, newTypeReference);
    effect.applyRoutine();
  }
  
  public void callCreateJavaPackage(final EObject sourceElementMappedToPackage, final org.emftext.language.java.containers.Package parentPackage, final String packageName, final String newTag) {
    mir.routines.pcm2java.CreateJavaPackageEffect effect = new mir.routines.pcm2java.CreateJavaPackageEffect(this.executionState, calledBy,
    	sourceElementMappedToPackage, parentPackage, packageName, newTag);
    effect.applyRoutine();
  }
  
  public void callRenameJavaPackage(final NamedElement sourceElementMappedToPackage, final org.emftext.language.java.containers.Package parentPackage, final String packageName, final String expectedTag) {
    mir.routines.pcm2java.RenameJavaPackageEffect effect = new mir.routines.pcm2java.RenameJavaPackageEffect(this.executionState, calledBy,
    	sourceElementMappedToPackage, parentPackage, packageName, expectedTag);
    effect.applyRoutine();
  }
  
  public void callDeleteJavaPackage(final NamedElement sourceElementMappedToPackage, final String packageName, final String expectedTag) {
    mir.routines.pcm2java.DeleteJavaPackageEffect effect = new mir.routines.pcm2java.DeleteJavaPackageEffect(this.executionState, calledBy,
    	sourceElementMappedToPackage, packageName, expectedTag);
    effect.applyRoutine();
  }
  
  public void callCreateJavaClass(final NamedElement sourceElementMappedToClass, final org.emftext.language.java.containers.Package containingPackage, final String className) {
    mir.routines.pcm2java.CreateJavaClassEffect effect = new mir.routines.pcm2java.CreateJavaClassEffect(this.executionState, calledBy,
    	sourceElementMappedToClass, containingPackage, className);
    effect.applyRoutine();
  }
  
  public void callCreateJavaInterface(final NamedElement sourceElementMappedToClass, final org.emftext.language.java.containers.Package containingPackage, final String className) {
    mir.routines.pcm2java.CreateJavaInterfaceEffect effect = new mir.routines.pcm2java.CreateJavaInterfaceEffect(this.executionState, calledBy,
    	sourceElementMappedToClass, containingPackage, className);
    effect.applyRoutine();
  }
  
  public void callCreateCompilationUnit(final NamedElement sourceElementMappedToClass, final ConcreteClassifier classifier, final org.emftext.language.java.containers.Package containingPackage) {
    mir.routines.pcm2java.CreateCompilationUnitEffect effect = new mir.routines.pcm2java.CreateCompilationUnitEffect(this.executionState, calledBy,
    	sourceElementMappedToClass, classifier, containingPackage);
    effect.applyRoutine();
  }
  
  public void callRenameJavaClassifier(final NamedElement classSourceElement, final org.emftext.language.java.containers.Package containingPackage, final String className) {
    mir.routines.pcm2java.RenameJavaClassifierEffect effect = new mir.routines.pcm2java.RenameJavaClassifierEffect(this.executionState, calledBy,
    	classSourceElement, containingPackage, className);
    effect.applyRoutine();
  }
  
  public void callDeleteJavaClassifier(final NamedElement sourceElement) {
    mir.routines.pcm2java.DeleteJavaClassifierEffect effect = new mir.routines.pcm2java.DeleteJavaClassifierEffect(this.executionState, calledBy,
    	sourceElement);
    effect.applyRoutine();
  }
  
  public void callAddProvidedRole(final OperationProvidedRole providedRole) {
    mir.routines.pcm2java.AddProvidedRoleEffect effect = new mir.routines.pcm2java.AddProvidedRoleEffect(this.executionState, calledBy,
    	providedRole);
    effect.applyRoutine();
  }
  
  public void callRemoveProvidedRole(final ProvidedRole providedRole) {
    mir.routines.pcm2java.RemoveProvidedRoleEffect effect = new mir.routines.pcm2java.RemoveProvidedRoleEffect(this.executionState, calledBy,
    	providedRole);
    effect.applyRoutine();
  }
  
  public void callAddRequiredRole(final OperationRequiredRole requiredRole) {
    mir.routines.pcm2java.AddRequiredRoleEffect effect = new mir.routines.pcm2java.AddRequiredRoleEffect(this.executionState, calledBy,
    	requiredRole);
    effect.applyRoutine();
  }
  
  public void callAddParameterAndAssignmentToConstructor(final NamedElement parameterCorrespondenceSource, final Constructor constructor, final NamespaceClassifierReference typeReference, final Field fieldToBeAssigned, final String parameterName) {
    mir.routines.pcm2java.AddParameterAndAssignmentToConstructorEffect effect = new mir.routines.pcm2java.AddParameterAndAssignmentToConstructorEffect(this.executionState, calledBy,
    	parameterCorrespondenceSource, constructor, typeReference, fieldToBeAssigned, parameterName);
    effect.applyRoutine();
  }
  
  public void callRemoveRequiredRole(final RequiredRole requiredRole, final InterfaceRequiringEntity requiringEntity) {
    mir.routines.pcm2java.RemoveRequiredRoleEffect effect = new mir.routines.pcm2java.RemoveRequiredRoleEffect(this.executionState, calledBy,
    	requiredRole, requiringEntity);
    effect.applyRoutine();
  }
  
  public void callRemoveCorrespondingParameterFromConstructor(final Constructor ctor, final NamedElement correspondenceSource) {
    mir.routines.pcm2java.RemoveCorrespondingParameterFromConstructorEffect effect = new mir.routines.pcm2java.RemoveCorrespondingParameterFromConstructorEffect(this.executionState, calledBy,
    	ctor, correspondenceSource);
    effect.applyRoutine();
  }
  
  public void callReinitializeOperationRequiredRole(final OperationRequiredRole requiredRole) {
    mir.routines.pcm2java.ReinitializeOperationRequiredRoleEffect effect = new mir.routines.pcm2java.ReinitializeOperationRequiredRoleEffect(this.executionState, calledBy,
    	requiredRole);
    effect.applyRoutine();
  }
  
  public void callCreateMethodForOperationSignature(final OperationSignature operationSignature) {
    mir.routines.pcm2java.CreateMethodForOperationSignatureEffect effect = new mir.routines.pcm2java.CreateMethodForOperationSignatureEffect(this.executionState, calledBy,
    	operationSignature);
    effect.applyRoutine();
  }
  
  public void callRenameMethodForOperationSignature(final OperationSignature operationSignature) {
    mir.routines.pcm2java.RenameMethodForOperationSignatureEffect effect = new mir.routines.pcm2java.RenameMethodForOperationSignatureEffect(this.executionState, calledBy,
    	operationSignature);
    effect.applyRoutine();
  }
  
  public void callChangeReturnTypeOfMethodForOperationSignature(final OperationSignature operationSignature) {
    mir.routines.pcm2java.ChangeReturnTypeOfMethodForOperationSignatureEffect effect = new mir.routines.pcm2java.ChangeReturnTypeOfMethodForOperationSignatureEffect(this.executionState, calledBy,
    	operationSignature);
    effect.applyRoutine();
  }
  
  public void callChangeInterfaceMethodReturnType(final InterfaceMethod interfaceMethod, final DataType returnType) {
    mir.routines.pcm2java.ChangeInterfaceMethodReturnTypeEffect effect = new mir.routines.pcm2java.ChangeInterfaceMethodReturnTypeEffect(this.executionState, calledBy,
    	interfaceMethod, returnType);
    effect.applyRoutine();
  }
  
  public void callDeleteMethodForOperationSignature(final OperationSignature operationSignature) {
    mir.routines.pcm2java.DeleteMethodForOperationSignatureEffect effect = new mir.routines.pcm2java.DeleteMethodForOperationSignatureEffect(this.executionState, calledBy,
    	operationSignature);
    effect.applyRoutine();
  }
  
  public void callCreateParameter(final Parameter parameter) {
    mir.routines.pcm2java.CreateParameterEffect effect = new mir.routines.pcm2java.CreateParameterEffect(this.executionState, calledBy,
    	parameter);
    effect.applyRoutine();
  }
  
  public void callRenameParameter(final Parameter parameter) {
    mir.routines.pcm2java.RenameParameterEffect effect = new mir.routines.pcm2java.RenameParameterEffect(this.executionState, calledBy,
    	parameter);
    effect.applyRoutine();
  }
  
  public void callChangeParameterType(final Parameter parameter) {
    mir.routines.pcm2java.ChangeParameterTypeEffect effect = new mir.routines.pcm2java.ChangeParameterTypeEffect(this.executionState, calledBy,
    	parameter);
    effect.applyRoutine();
  }
  
  public void callDeleteParameter(final OperationSignature signature, final Parameter parameter) {
    mir.routines.pcm2java.DeleteParameterEffect effect = new mir.routines.pcm2java.DeleteParameterEffect(this.executionState, calledBy,
    	signature, parameter);
    effect.applyRoutine();
  }
  
  public void callCreateMethodForResourceDemandingBehavior(final ResourceDemandingInternalBehaviour behavior) {
    mir.routines.pcm2java.CreateMethodForResourceDemandingBehaviorEffect effect = new mir.routines.pcm2java.CreateMethodForResourceDemandingBehaviorEffect(this.executionState, calledBy,
    	behavior);
    effect.applyRoutine();
  }
  
  public void callRenameMethodForResourceDemandingBehavior(final ResourceDemandingInternalBehaviour behavior) {
    mir.routines.pcm2java.RenameMethodForResourceDemandingBehaviorEffect effect = new mir.routines.pcm2java.RenameMethodForResourceDemandingBehaviorEffect(this.executionState, calledBy,
    	behavior);
    effect.applyRoutine();
  }
  
  public void callDeleteMethodForResourceDemandingBehavior(final ResourceDemandingInternalBehaviour behavior) {
    mir.routines.pcm2java.DeleteMethodForResourceDemandingBehaviorEffect effect = new mir.routines.pcm2java.DeleteMethodForResourceDemandingBehaviorEffect(this.executionState, calledBy,
    	behavior);
    effect.applyRoutine();
  }
  
  public void callCreateSEFF(final ServiceEffectSpecification seff) {
    mir.routines.pcm2java.CreateSEFFEffect effect = new mir.routines.pcm2java.CreateSEFFEffect(this.executionState, calledBy,
    	seff);
    effect.applyRoutine();
  }
  
  public void callChangeMethodForSeff(final ResourceDemandingSEFF seff) {
    mir.routines.pcm2java.ChangeMethodForSeffEffect effect = new mir.routines.pcm2java.ChangeMethodForSeffEffect(this.executionState, calledBy,
    	seff);
    effect.applyRoutine();
  }
  
  public void callUpdateSEFFImplementingMethodName(final ServiceEffectSpecification seff) {
    mir.routines.pcm2java.UpdateSEFFImplementingMethodNameEffect effect = new mir.routines.pcm2java.UpdateSEFFImplementingMethodNameEffect(this.executionState, calledBy,
    	seff);
    effect.applyRoutine();
  }
  
  public void callDeleteMethodForSeff(final ServiceEffectSpecification seff) {
    mir.routines.pcm2java.DeleteMethodForSeffEffect effect = new mir.routines.pcm2java.DeleteMethodForSeffEffect(this.executionState, calledBy,
    	seff);
    effect.applyRoutine();
  }
}
