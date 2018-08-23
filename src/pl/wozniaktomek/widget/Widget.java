package pl.wozniaktomek.widget;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Widget {
    private HashMap<WidgetStyle, ArrayList<String>> styles;
    private VBox mainContainer;
    VBox contentContainer;

    private Text title;
    private HBox titleContainer;

    Widget() {
        initializeStyles();
        initializeMainContainer();
        initializeTitleContainer();
        initializeContentContainer();
        setStyle(WidgetStyle.PRIMARY);
    }

    public VBox getWidget() {
        return mainContainer;
    }

    void setTitle(String titleText) {
        title.setText(titleText);
    }

    public void setStyle(WidgetStyle widgetStyle) {
        for (WidgetStyle style : WidgetStyle.values()) {
            if (widgetStyle.equals(style)) {
                mainContainer.getStyleClass().add(styles.get(style).get(0));
                titleContainer.getStyleClass().add(styles.get(style).get(1));
            } else {
                mainContainer.getStyleClass().remove(styles.get(style).get(0));
                titleContainer.getStyleClass().remove(styles.get(style).get(1));
            }
        }
    }

    private void initializeStyles() {
        styles = new HashMap<>();

        ArrayList<String> styleClasses = new ArrayList<>();
        styleClasses.add("widget-primary");
        styleClasses.add("widget-primary-background-fill");
        styles.put(WidgetStyle.PRIMARY, styleClasses);

        styleClasses = new ArrayList<>();
        styleClasses.add("widget-secondary");
        styleClasses.add("widget-secondary-background-fill");
        styles.put(WidgetStyle.SECONDARY, styleClasses);

        styleClasses = new ArrayList<>();
        styleClasses.add("widget-success");
        styleClasses.add("widget-success-background-fill");
        styles.put(WidgetStyle.SUCCESS, styleClasses);

        styleClasses = new ArrayList<>();
        styleClasses.add("widget-failure");
        styleClasses.add("widget-failure-background-fill");
        styles.put(WidgetStyle.FAILURE, styleClasses);
    }

    private void initializeMainContainer() {
        mainContainer = new VBox();
        mainContainer.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        mainContainer.setAlignment(Pos.TOP_LEFT);

    }

    private void initializeTitleContainer() {
        titleContainer = new HBox();
        titleContainer.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        titleContainer.setPadding(new Insets(12));
        titleContainer.setSpacing(12d);

        title = new Text("Widget");
        title.getStyleClass().add("section-title-background");

        titleContainer.getChildren().add(title);
        mainContainer.getChildren().add(titleContainer);
    }

    private void initializeContentContainer() {
        contentContainer = new VBox();
        contentContainer.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        contentContainer.setPadding(new Insets(12));
        contentContainer.setSpacing(12d);

        mainContainer.getChildren().add(contentContainer);
    }


    public enum WidgetStyle {PRIMARY, SECONDARY, SUCCESS, FAILURE}
}
