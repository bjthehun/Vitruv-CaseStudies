package tools.vitruv.applications.umljava;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import tools.vitruv.framework.applications.VitruvApplication;
import tools.vitruv.framework.domains.VitruvDomain;
import tools.vitruv.change.propagation.ChangePropagationSpecification;

public class UmlJavaApplication implements VitruvApplication {

	@Override
	public Set<ChangePropagationSpecification> getChangePropagationSpecifications() {
		Set<ChangePropagationSpecification> specs = new HashSet<ChangePropagationSpecification>();
		specs.add(new UmlToJavaChangePropagationSpecification());
		specs.add(new JavaToUmlChangePropagationSpecification());
		return specs;
	}

	@Override
	public String getName() {
		return "UML <> Java";
	}

	@Override
	public Set<VitruvDomain> getVitruvDomains() {
		return Collections.emptySet();
	}
}
