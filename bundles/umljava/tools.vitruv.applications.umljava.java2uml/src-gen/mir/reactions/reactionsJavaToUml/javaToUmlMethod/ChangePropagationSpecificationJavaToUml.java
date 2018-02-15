package mir.reactions.reactionsJavaToUml.javaToUmlMethod;

import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionsChangePropagationSpecification;

@SuppressWarnings("all")
public class ChangePropagationSpecificationJavaToUml extends AbstractReactionsChangePropagationSpecification {
  public ChangePropagationSpecificationJavaToUml() {
    super(new tools.vitruv.domains.java.JavaDomainProvider().getDomain(), 
    	new tools.vitruv.domains.uml.UmlDomainProvider().getDomain());
  }
  
  protected void setup() {
    this.addChangeMainprocessor(new mir.reactions.reactionsJavaToUml.javaToUmlMethod.ExecutorJavaToUml());
  }
}
