package uk.gov.hmcts.reform.wacaseeventhandler.domain.handlers.initiatetask;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import uk.gov.hmcts.reform.wacaseeventhandler.domain.handlers.common.DmnStringValue;
import uk.gov.hmcts.reform.wacaseeventhandler.domain.handlers.common.EvaluateRequest;

@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
public final class InitiateEvaluateRequest extends EvaluateRequest {
    private final DmnStringValue eventId;
    private final DmnStringValue postEventState;
    private final DmnStringValue now;
    private final DmnStringValue directionDueDate;

    public InitiateEvaluateRequest(DmnStringValue eventId,
                                   DmnStringValue postEventState,
                                   DmnStringValue now,
                                   DmnStringValue directionDueDate) {
        super();
        this.eventId = eventId;
        this.postEventState = postEventState;
        this.now = now;
        this.directionDueDate = directionDueDate;
    }

    public DmnStringValue getEventId() {
        return eventId;
    }

    public DmnStringValue getPostEventState() {
        return postEventState;
    }

    public DmnStringValue getNow() {
        return now;
    }

    public DmnStringValue getDirectionDueDate() {
        return directionDueDate;
    }
}
