package com.tbleier.kitchenlist.adapter.in.views.interceptors;

import com.tbleier.kitchenlist.application.ports.in.CommandResult;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.out.NonUniqueException;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class CommandExceptionHandler<T> implements CommandService<T> {

    private final CommandService<T> decoratee;
    public Notification notification;

    public CommandExceptionHandler(CommandService<T> decoratee) {
        this.decoratee = decoratee;
    }

    @Override
    public CommandResult execute(T command) {
        var text = new Div(new Text(""));
        configureNotification(text);

        try {
            return decoratee.execute(command);
        }
        catch (NonUniqueException nonUniqueException) {
            text.setText(nonUniqueException.getMessage());
        }
        catch(Exception e) {
            text.setText(e.getMessage());
        }

        if(!text.getText().isEmpty())
            notification.open();

        return new CommandResult(false);
    }

    private void configureNotification(Div text) {
        notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        Button closeButton = new Button(new Icon("lumo", "cross"));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.getElement().setAttribute("aria-label", "Close");
        closeButton.addClickListener(event -> {
            notification.close();
        });

        HorizontalLayout layout = new HorizontalLayout(text, closeButton);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        notification.add(layout);
    }
}
