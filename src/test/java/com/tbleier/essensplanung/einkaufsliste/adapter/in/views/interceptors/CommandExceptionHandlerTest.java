package com.tbleier.essensplanung.einkaufsliste.adapter.in.views.interceptors;

import com.tbleier.essensplanung.einkaufsliste.application.ports.in.CommandService;
import com.tbleier.essensplanung.einkaufsliste.application.ports.out.NonUniqueException;
import com.vaadin.flow.component.UI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommandExceptionHandlerTest {

    private class SomeCommand {

    }

    private SomeCommand someCommand;

    @Mock
    private CommandService<SomeCommand> decoratee;

    private CommandExceptionHandler<SomeCommand> testee;

    @BeforeEach
    public void setUp() {
        someCommand = new SomeCommand();
        UI.setCurrent(Mockito.mock(UI.class));
        testee = new CommandExceptionHandler(decoratee);
    }

    @Test
    public void should_handle_NonUniqueExceptions() {
        //Arrange
        givenDecorateeThrowsA(new NonUniqueException("maybe a doublette"));

        //Act
        testee.execute(new SomeCommand());
    
        //Assert
        assertTrue(testee.notification.isOpened());
    }

    @Test
    public void should_just_call_decoratee_when_no_exception_is_thrown() {
        //Act
        testee.execute(someCommand);

        //Assert
        assertFalse(testee.notification.isOpened());
        verify(decoratee).execute(someCommand);
    }

    private void givenDecorateeThrowsA(Exception exception) {
        doThrow(exception).when(decoratee).execute(any());
    }
}