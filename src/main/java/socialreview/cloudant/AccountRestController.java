package socialreview.cloudant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.IndexField;
import com.cloudant.client.api.model.IndexField.SortOrder;
import com.cloudant.client.api.model.Response;


@RestController
@RequestMapping("/account")
public class AccountRestController {

    @Autowired
    private Database db;

    // Create a new account
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveAccount(@RequestBody Account account) {
        System.out.println("Save account " + account);
        Response r = null;
        if (account != null) {
            r = db.post(account);
        }
        return r.getId();
    }

    // Query accounts for all documents or by accountID
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List<Account> getAll(@RequestParam(required=false) Integer accountId) {
        // Get all documents from accounts db
        List<Account> allDocs = null;
        try {
            if (accountId == null) {
                allDocs = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse()
                            .getDocsAs(Account.class);
            } else {
                // create Index
                // Here is create a design doc named designdoc
                // A view named querybyaccountIdView
                // and an index named accountId
                db.createIndex("querybyaccountIdView","designdoc","json",
                    new IndexField[]{new IndexField("accountId",SortOrder.asc)});
                System.out.println("Successfully created index");
                allDocs = db.findByIndex("{\"accountId\" : " + accountId + "}", Account.class);
            }
        } catch (Exception e) {
            System.out.println("Exception thrown : " + e.getMessage());
        }
        return allDocs;
    }
}
