package com.tbleier.essensplanung.adapter.in.views.rezept;

import com.tbleier.essensplanung.adapter.in.views.MainLayout;
import com.tbleier.essensplanung.application.ports.KategorieDTO;
import com.tbleier.essensplanung.application.ports.RezeptKategorieDTO;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;


@PageTitle("Rezepte")
@Route(value = "rezepte", layout = MainLayout.class)

public class RezepteListView extends VerticalLayout {

    Grid<KategorieDTO> grid = new Grid<>(KategorieDTO.class);

    ListBox<RezeptKategorieDTO> listBox = new ListBox<>();
    Button addRezeptKategorieButton;

    private List<KategorieDTO> kategorieDTOS;

    @Autowired
    public RezepteListView() {
        kategorieDTOS = new LinkedList<>();

        addClassName("kategorie-list-view");
        setSizeFull();

        listBox.setItems(List.of(new RezeptKategorieDTO(), new RezeptKategorieDTO(), new RezeptKategorieDTO()));

        listBox.setRenderer(new ComponentRenderer<>(rezeptKategorieDTO -> {
            HorizontalLayout cardLayout = new HorizontalLayout();
            cardLayout.setMargin(true);

            Avatar avatar = new Avatar();
            avatar.setName("AvatarName");
            avatar.setImage("https://www.google.com/url?sa=i&url=https%3A%2F%2Frivierabarcrawltours.com%2Fde%2Fberuehmtes-essen-in-nizza-frankreich%2F&psig=AOvVaw2ZDDl-det1zxwlNS39brQL&ust=1681207533130000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCIjgn6GIn_4CFQAAAAAdAAAAABAE");
            avatar.setHeight("80px");
            avatar.setWidth("80px");

            VerticalLayout infoLayout = new VerticalLayout();
            infoLayout.setSpacing(false);
            infoLayout.setPadding(false);
            infoLayout.getElement().appendChild(
                    ElementFactory.createStrong("Sonntagsgerichte"));

            cardLayout.add(avatar, infoLayout);
            return cardLayout;
        }));

        add(getToolbar(),listBox);
        closeEditor();
    }

    private void closeEditor() {
        removeClassName("editing");
    }

    private Component getToolbar() {

        addRezeptKategorieButton = new Button("Rezeptkategorie hinzufügen");

        HorizontalLayout toolbar = new HorizontalLayout(addRezeptKategorieButton);
        toolbar.addClassName("rezeptKategorie-toolbar");

        return toolbar;
    }

}
