package ca.mcgill.ecse.assetplus.javafx.controllers;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet2Controller;
import static ca.mcgill.ecse.assetplus.javafx.controllers.ViewUtils.successful;
import java.sql.Date;
import static ca.mcgill.ecse.assetplus.javafx.controllers.ViewUtils.sceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
public class UpdateAssetTypeController {


  @FXML
  private TextField updateName;

  @FXML
  private TextField updateExpectedLifespan;

  @FXML
  private Button cancelUpdateAssetType;

  @FXML
  private Button updateAssetType;

  @FXML
  private AnchorPane updateAssetTypeAnchorPane;

  @FXML
  void cancelUpdateAssetTypeClicked(ActionEvent event) {
    updateName.setText("");
    updateExpectedLifespan.setText("");
    sceneSwitch(updateAssetTypeAnchorPane, "../pages/AssetTypeView.fxml");
  }

  @FXML
  void updateAssetTypeClicked(ActionEvent event) {
    String oldName = "lamp";
    String name = updateName.getText();
    Integer expectedLifespan = Integer.parseInt(updateExpectedLifespan.getText());
    if (successful(AssetPlusFeatureSet2Controller.updateAssetType(oldName, name, expectedLifespan))) {
      updateName.setText("");
      updateExpectedLifespan.setText("");
      sceneSwitch(updateAssetTypeAnchorPane, "../pages/AssetTypeView.fxml");
    }
  }

  public void setName(String name) {
    updateName.setText(name);
  }

  public void setExpectedLifeSpan(Integer expectedLifespan) {
    updateExpectedLifespan.setText(expectedLifespan != null ? expectedLifespan.toString() : "");
  }
}
