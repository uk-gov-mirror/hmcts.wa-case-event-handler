package uk.gov.hmcts.reform.wacaseeventhandler.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@SuppressWarnings("PMD.GenericsNaming")
public final class EvaluateDmnRequest<RequestT> {

    private final RequestT variables;

    public EvaluateDmnRequest(RequestT variables) {
        this.variables = variables;
    }

    public RequestT getVariables() {
        return variables;
    }
}
