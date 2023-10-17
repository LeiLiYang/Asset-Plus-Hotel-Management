package ca.mcgill.ecse.assetplus.features;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;
import ca.mcgill.ecse.assetplus.model.Guest;
import ca.mcgill.ecse.assetplus.model.Manager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteGuestStepDefinitions {
  // "global" variables to be used in step definitions
  private AssetPlus ap;
  private String error;

  /**
   * @author William Wang
   * @param dataTable
   */
  @Given("the following guests exist in the system \\(p8)")
  public void the_following_guests_exist_in_the_system_p8(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String email = row.get("email");
      String password = row.get("password");
      String name = row.get("name");
      String phoneNumber = row.get("phoneNumber");
      ap.addGuest(email, name, password, phoneNumber);
    }
  }

  /**
   * @author Krasimir Kirov
   * @param dataTable
   */
  @Given("the following manager exists in the system \\(p8)")
  public void the_following_manager_exists_in_the_system_p8(
      io.cucumber.datatable.DataTable dataTable) {
        AssetPlus ap = AssetPlusApplication.getAssetPlus();
        Map<String, String> row = dataTable.asMap(String.class, String.class);
        
        String name = row.get("key:name");
        String email = row.get("key:email");
        String password = row.get("key:password");
        String phoneNumber = row.get("key:phoneNumber");
        //Correct idea?
        if (ap.hasManager()) {
          Manager existingManager = ap.getManager();
          existingManager.setName(name);
          existingManager.setEmail(email);
          existingManager.setPassword(password);
          existingManager.setPhoneNumber(phoneNumber);
        } else {
          Manager newManager = new Manager(name, email, password, phoneNumber, ap);
          ap.setManager(newManager);
        }
  }

  /**
   * @author Michael Rafferty
   * @param dataTable
   */
  @When("the guest attempts to delete their own account linked to the {string} \\(p8)")
  public void the_guest_attempts_to_delete_their_own_account_linked_to_the_p8(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @author Vlad Arama
   * @param dataTable
   */
  @Then("the guest account linked to {string} shall not exist in the system \\(p8)")
  public void the_guest_account_linked_to_shall_not_exist_in_the_system_p8(String string) {
        AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
        List<Guest> guestsList = assetPlus.getGuests();
        for (Guest guest : guestsList) {
            assertNotEquals("Guest with the same email has been found in the system.", string, guest.getEmail());
        }
  }

  /**
   * @author Li Yang Lei
   * @param dataTable
   */
  @Then("the manager account linked to {string} shall exist in the system \\(p8)")
  public void the_manager_account_linked_to_shall_exist_in_the_system_p8(String string) {
    AssetPlus ap = AssetPlusApplication.getAssetPlus();
    Manager manager = ap.getManager();
    assertEquals("The manager account linked to " + string + " does not exist in the system.", manager.getEmail(), string);
  }

  /**
   * @author Tim Pham
   * @param dataTable
   */
  @Then("the number of guests in the system shall be {string} \\(p8)")
  public void the_number_of_guests_in_the_system_shall_be_p8(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
}
