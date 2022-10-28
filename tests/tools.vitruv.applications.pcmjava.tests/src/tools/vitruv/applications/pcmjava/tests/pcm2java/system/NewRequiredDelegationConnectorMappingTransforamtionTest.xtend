package tools.vitruv.applications.pcmjava.tests.pcm2java.system

import java.util.List
import org.junit.jupiter.api.Test
import org.palladiosimulator.pcm.core.composition.CompositionFactory
import org.palladiosimulator.pcm.core.composition.RequiredDelegationConnector
import org.palladiosimulator.pcm.repository.OperationRequiredRole
import tools.vitruv.applications.pcmjava.tests.pcm2java.Pcm2JavaTestUtils
import tools.vitruv.applications.pcmjava.tests.pcm2java.Pcm2JavaTransformationTest
import tools.vitruv.applications.pcmjava.tests.pcm2java.javahelper.ConstructorArguments
import tools.vitruv.applications.pcmjava.tests.pcm2java.javahelper.JavaClassBuilder
import tools.vitruv.applications.pcmjava.tests.pcm2java.javahelper.JavaInterfaceBuilder

import static tools.vitruv.applications.pcmjava.tests.pcm2java.PcmCreatorsUtil.*
import static tools.vitruv.applications.pcmjava.tests.pcm2java.javahelper.JavaCreatorsUtil.*

import static extension edu.kit.ipd.sdq.commons.util.java.lang.IterableUtil.*

class NewRequiredDelegationConnectorMappingTransforamtionTest extends Pcm2JavaTransformationTest {
	
	@Test
	def void testAddRequireDelegationConnector() {
		 createRepostory(Pcm2JavaTestUtils.REPOSITORY_NAME)
		 createSystem(Pcm2JavaTestUtils.SYSTEM_NAME)
		 val basicComponent = createBasicComponent(Pcm2JavaTestUtils.BASIC_COMPONENT_NAME)
		 val operationInterface = createOperationInterface(Pcm2JavaTestUtils.INTERFACE_NAME)
		 changePcmView [
		 	modifySingleRepository [
		 		interfaces__Repository += operationInterface
		 		components__Repository += basicComponent
		 		basicComponent.requiredRoles_InterfaceRequiringEntity += createOperationRequiredRole(operationInterface, basicComponent)
		 	]
		 	modifySingleSystem [
		 		assemblyContexts__ComposedStructure += createAssemblyContext(basicComponent, Pcm2JavaTestUtils.ASSEMBLY_CONTEXT_NAME)
		 		requiredRoles_InterfaceRequiringEntity += createOperationRequiredRole(operationInterface, it)
		 	]
		 ]
		 
		 changePcmView [
		 	modifySingleSystem [
		 		val assemblyContext = it.assemblyContexts__ComposedStructure.claimOne
		 		val systemRequiredRole = it.requiredRoles_InterfaceRequiringEntity.filter(OperationRequiredRole).claimOne
		 		val basicComponentRequiredRole = basicComponent.requiredRoles_InterfaceRequiringEntity.filter(OperationRequiredRole).claimOne
		 		
		 		val RequiredDelegationConnector requiredDelegationConnector = CompositionFactory.eINSTANCE.createRequiredDelegationConnector();
				requiredDelegationConnector.setAssemblyContext_RequiredDelegationConnector(assemblyContext);
				requiredDelegationConnector.setInnerRequiredRole_RequiredDelegationConnector(basicComponentRequiredRole);
				requiredDelegationConnector.setOuterRequiredRole_RequiredDelegationConnector(systemRequiredRole);
				
				connectors__ComposedStructure += requiredDelegationConnector
		 	]
		 ]
		 
		 validateJavaView [
		 	val interfaceCompilationUnit = new JavaInterfaceBuilder(Pcm2JavaTestUtils.INTERFACE_NAME, Pcm2JavaTestUtils.REPOSITORY_NAME + Pcm2JavaTestUtils.CONTRACTS_SUFIX).build
		 	val basicComponentCompilationUnit = new JavaClassBuilder(
					Pcm2JavaTestUtils.BASIC_COMPONENT_NAME + Pcm2JavaTestUtils.IMPL_SUFIX, 
					Pcm2JavaTestUtils.REPOSITORY_NAME + "." + Pcm2JavaTestUtils.BASIC_COMPONENT_NAME
				)
				.addImportWithNamespace(interfaceCompilationUnit)
				.addPrivateField(getRequiredInterfacFieldOrVariableName(Pcm2JavaTestUtils.INTERFACE_NAME, Pcm2JavaTestUtils.BASIC_COMPONENT_NAME), getReference(interfaceCompilationUnit))
				.addConstructorInitalizationForField(getRequiredInterfacFieldOrVariableName(Pcm2JavaTestUtils.INTERFACE_NAME, Pcm2JavaTestUtils.BASIC_COMPONENT_NAME))
				.build
			val systemCompilationUnit = new JavaClassBuilder(
					Pcm2JavaTestUtils.SYSTEM_NAME + Pcm2JavaTestUtils.IMPL_SUFIX,
					Pcm2JavaTestUtils.SYSTEM_NAME_CAMELCASE
				)
				.addImportWithNamespace(basicComponentCompilationUnit)
				.addImportWithNamespace(interfaceCompilationUnit)
				.addPrivateField(Pcm2JavaTestUtils.ASSEMBLY_CONTEXT_NAME, getReference(basicComponentCompilationUnit))
				.addPrivateField(getRequiredInterfacFieldOrVariableName(Pcm2JavaTestUtils.INTERFACE_NAME, Pcm2JavaTestUtils.SYSTEM_NAME), getReference(interfaceCompilationUnit))
				.addConstructorConstructionForField(Pcm2JavaTestUtils.ASSEMBLY_CONTEXT_NAME, ConstructorArguments.WITH_NULL_LITERAL)
				.addConstructorInitalizationForField(getRequiredInterfacFieldOrVariableName(Pcm2JavaTestUtils.INTERFACE_NAME, Pcm2JavaTestUtils.SYSTEM_NAME))
				.build
		 	assertCompilationUnits(List.of(interfaceCompilationUnit, basicComponentCompilationUnit, systemCompilationUnit))
		 ]
	}
}