package edu.kit.ipd.sdq.vitruvius.tests.casestudies.pcmjava.transformations.pcm2jamopp.repository;

import org.junit.Test;

import de.uka.ipd.sdq.pcm.repository.InnerDeclaration;
import de.uka.ipd.sdq.pcm.repository.PrimitiveDataType;
import de.uka.ipd.sdq.pcm.repository.PrimitiveTypeEnum;
import de.uka.ipd.sdq.pcm.repository.RepositoryFactory;
import edu.kit.ipd.sdq.vitruvius.framework.contracts.datatypes.VURI;
import edu.kit.ipd.sdq.vitruvius.tests.casestudies.pcmjava.transformations.pcm2jamopp.PCM2JaMoPPTransformationTest;
import edu.kit.ipd.sdq.vitruvius.tests.casestudies.pcmjava.transformations.utils.PCM2JaMoPPUtils;

public class InnerDeclarationMappingTransformationTest extends PCM2JaMoPPTransformationTest {

    @Test
    public void testAddInnerDeclaration() throws Throwable {
        final InnerDeclaration innerDec = this.createAndSyncRepositoryCompositeDataTypeAndInnerDeclaration();

        this.assertInnerDeclaration(innerDec);
    }

    @Test
    public void testRenameInnerDeclaration() throws Throwable {
        final InnerDeclaration innerDec = this.createAndSyncRepositoryCompositeDataTypeAndInnerDeclaration();

        innerDec.setEntityName(PCM2JaMoPPUtils.INNER_DEC_NAME + PCM2JaMoPPUtils.RENAME);
        super.triggerSynchronization(VURI.getInstance(innerDec.eResource()));

        this.assertInnerDeclaration(innerDec);
    }

    @Test
    public void testChangeInnerDeclarationType() throws Throwable {
        final InnerDeclaration innerDec = this.createAndSyncRepositoryCompositeDataTypeAndInnerDeclaration();

        final PrimitiveDataType newPDT = RepositoryFactory.eINSTANCE.createPrimitiveDataType();
        newPDT.setType(PrimitiveTypeEnum.STRING);
        innerDec.setDatatype_InnerDeclaration(newPDT);
        super.triggerSynchronization(VURI.getInstance(innerDec.eResource()));

        this.assertInnerDeclaration(innerDec);
    }

}
