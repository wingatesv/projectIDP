package common;

import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;

/**
 * 
 * Uses a combo box tool tip as the suggestion for auto complete and updates the
 * combo box items accordingly <br />
 *
 * @param <T>
 */
public class ComboBoxAutoComplete<T> {

	private ComboBox<T> cmb;
	String filter = "";
	private ObservableList<T> originalItems;

	public ComboBoxAutoComplete(ComboBox<T> cmb) {
		this.cmb = cmb;
		originalItems = FXCollections.observableArrayList(cmb.getItems());
		cmb.setTooltip(new Tooltip());
		cmb.setOnKeyPressed(this::handleOnKeyPressed);
		cmb.setOnHidden(this::handleOnHiding);
	}

	public void handleOnKeyPressed(KeyEvent e) {
		ObservableList<T> filteredList = FXCollections.observableArrayList();	// create an observable list
		KeyCode code = e.getCode();			// get what key is pressed

		if (code.isLetterKey()) {
			filter += e.getText();		// append the keyword into string if the key is a letter
		}
		if (code == KeyCode.BACK_SPACE && filter.length() > 0) {
			filter = filter.substring(0, filter.length() - 1);		// delete the keyword accordingly when the user press back space
			cmb.getItems().setAll(originalItems);
		}
		if (code == KeyCode.ESCAPE) {
			filter = "";		// clear all keywords when user press escape 
		}
		if (filter.length() == 0) {
			filteredList = originalItems;		// show original list when there is no keyword registered
			cmb.getTooltip().hide();
		} else {
			Stream<T> itens = cmb.getItems().stream();		// show items in combo box
			String txtUsr = filter.toString().toLowerCase();	// change all keywords into lower case
			itens.filter(el -> el.toString().toLowerCase().contains(txtUsr)).forEach(filteredList::add);	// show the items that contain the key words and add more item accordingly
			cmb.getTooltip().setText(txtUsr);		// set text on the pop up window
			Window stage = cmb.getScene().getWindow();
			double posX = stage.getX() + cmb.getBoundsInParent().getMinX();	// re-adjust the window
			double posY = stage.getY() + cmb.getBoundsInParent().getMinY(); // re-adjust the window
			cmb.getTooltip().show(stage, posX, posY);
			cmb.show();
		}
		cmb.getItems().setAll(filteredList);
	}

	public void handleOnHiding(Event e) {
		filter = "";
		cmb.getTooltip().hide();
		T s = cmb.getSelectionModel().getSelectedItem();
		cmb.getItems().setAll(originalItems);
		cmb.getSelectionModel().select(s);
	}

}
