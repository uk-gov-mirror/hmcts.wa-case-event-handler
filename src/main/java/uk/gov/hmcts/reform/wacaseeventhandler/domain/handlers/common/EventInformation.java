package uk.gov.hmcts.reform.wacaseeventhandler.domain.handlers.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Locale;
import javax.validation.constraints.NotEmpty;

@ToString
@EqualsAndHashCode
@Builder
@SuppressWarnings("PMD.ExcessiveParameterList")
public final class EventInformation {

    @NotEmpty
    private final String eventInstanceId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private final LocalDateTime eventTimeStamp;
    @NotEmpty
    private final String caseId;
    @NotEmpty
    private final String jurisdictionId;
    @NotEmpty
    private final String caseTypeId;
    @NotEmpty
    private final String eventId;
    private final String previousStateId;
    private final String newStateId;
    @NotEmpty
    private final String userId;
    private final AdditionalData additionalData;

    @JsonCreator
    public EventInformation(@JsonProperty("EventInstanceId") String eventInstanceId,
                            @JsonProperty("EventTimeStamp")LocalDateTime eventTimeStamp,
                            @JsonProperty("CaseId") String caseId,
                            @JsonProperty("JurisdictionId") String jurisdictionId,
                            @JsonProperty("CaseTypeId") String caseTypeId,
                            @JsonProperty("EventId") String eventId,
                            @JsonProperty("PreviousStateId") String previousStateId,
                            @JsonProperty("NewStateId") String newStateId,
                            @JsonProperty("UserId") String userId,
                            @JsonProperty("AdditionalData") AdditionalData additionalData) {
        this.eventInstanceId = eventInstanceId;
        this.eventTimeStamp = eventTimeStamp;
        this.caseId = caseId;
        this.jurisdictionId = jurisdictionId.toLowerCase(Locale.ENGLISH);
        this.caseTypeId = caseTypeId.toLowerCase(Locale.ENGLISH);
        this.eventId = eventId;
        this.previousStateId = previousStateId;
        this.newStateId = newStateId;
        this.userId = userId;
        this.additionalData = additionalData;
    }

    public String getJurisdictionId() {
        return jurisdictionId;
    }

    public String getCaseTypeId() {
        return caseTypeId;
    }

    public String getEventInstanceId() {
        return eventInstanceId;
    }

    public LocalDateTime getEventTimeStamp() {
        return eventTimeStamp;
    }

    public String getCaseId() {
        return caseId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getPreviousStateId() {
        return previousStateId;
    }

    public String getNewStateId() {
        return newStateId;
    }

    public String getUserId() {
        return userId;
    }

    public AdditionalData getAdditionalData() {
        return additionalData;
    }
}
