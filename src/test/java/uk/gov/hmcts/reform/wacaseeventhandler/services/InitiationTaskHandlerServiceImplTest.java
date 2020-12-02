package uk.gov.hmcts.reform.wacaseeventhandler.services;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InitiationTaskHandlerServiceImplTest {

    private final InitiationTaskHandlerServiceImpl handlerService = new InitiationTaskHandlerServiceImpl();

    @Test
    void can_handle() {
        assertThat(handlerService.canHandle()).isFalse();
    }

}
