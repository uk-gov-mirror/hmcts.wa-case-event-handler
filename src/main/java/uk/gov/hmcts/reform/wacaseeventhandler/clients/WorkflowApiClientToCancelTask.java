package uk.gov.hmcts.reform.wacaseeventhandler.clients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGenerator;
import uk.gov.hmcts.reform.wacaseeventhandler.domain.handler.cancellationtask.CancellationTaskEvaluateDmnResponse;
import uk.gov.hmcts.reform.wacaseeventhandler.domain.handler.common.EvaluateDmnRequest;
import uk.gov.hmcts.reform.wacaseeventhandler.domain.handler.common.EvaluateDmnResponse;
import uk.gov.hmcts.reform.wacaseeventhandler.domain.handler.common.SendMessageRequest;
import uk.gov.hmcts.reform.wacaseeventhandler.domain.handler.common.TaskEvaluateDmnRequest;
import uk.gov.hmcts.reform.wacaseeventhandler.domain.handler.common.TaskSendMessageRequest;

@Service
@Slf4j
public class WorkflowApiClientToCancelTask implements WorkflowApiClient {

    private final RestTemplate restTemplate;
    private final AuthTokenGenerator authTokenGenerator;
    private final String workflowApiUrl;

    public WorkflowApiClientToCancelTask(RestTemplate restTemplate,
                                         AuthTokenGenerator authTokenGenerator,
                                         @Value("${wa-workflow-api.url}") String workflowApiUrl) {
        this.restTemplate = restTemplate;
        this.authTokenGenerator = authTokenGenerator;
        this.workflowApiUrl = workflowApiUrl;
    }

    @Override
    public EvaluateDmnResponse<CancellationTaskEvaluateDmnResponse> evaluateDmn(
        String key,
        EvaluateDmnRequest<? extends TaskEvaluateDmnRequest> requestParameters
    ) {
        return makePostCall(key, requestParameters).getBody();
    }

    private ResponseEntity<EvaluateDmnResponse<CancellationTaskEvaluateDmnResponse>> makePostCall(
        String key,
        EvaluateDmnRequest<? extends TaskEvaluateDmnRequest> requestParameters
    ) {
        return restTemplate.exchange(
            String.format("%s/workflow/decision-definition/key/%s/evaluate", workflowApiUrl, key),
            HttpMethod.POST,
            new HttpEntity<>(requestParameters, buildHttpHeaders()),
            new ParameterizedTypeReference<>() {
            }
        );
    }

    private HttpHeaders buildHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("ServiceAuthorization", authTokenGenerator.generate());
        return headers;
    }

    @Override
    public ResponseEntity<Void> sendMessage(SendMessageRequest<? extends TaskSendMessageRequest> sendMessageRequest) {
        return restTemplate.exchange(
            String.format("%s/workflow/message", workflowApiUrl),
            HttpMethod.POST,
            new HttpEntity<>(sendMessageRequest, buildHttpHeaders()),
            Void.class
        );
    }

}
