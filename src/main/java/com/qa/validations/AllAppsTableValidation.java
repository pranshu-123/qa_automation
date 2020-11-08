package com.qa.validations;

import com.qa.enums.AllAppsEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class AllAppsTableValidation {

    public static void validateValueExistInTableForKilledStatus(List<WebElement> tableRows) {
        for (WebElement row: tableRows) {
            if (row.findElements(By.xpath("//td")).get(AllAppsEnum.STATUS.getIndex())
                    .getText().trim().equals("Killed")) {
                Assert.assertTrue(row.findElements(By.xpath("//td")).get(AllAppsEnum.USER.getIndex())
                        .getText() != "", "User information is empty.");
                Assert.assertTrue(row.findElements(By.xpath("//td")).get(AllAppsEnum.TYPE.getIndex())
                        .getText() != "", "Type information is empty.");
                Assert.assertTrue(row.findElements(By.xpath("//td")).get(AllAppsEnum.CLUSTER_ID.getIndex())
                        .getText() != "", "Cluster information is empty.");
                Assert.assertTrue(row.findElements(By.xpath("//td")).get(AllAppsEnum.DURATION.getIndex())
                        .getText() != "", "Workflow Duration information is empty.");
                Assert.assertTrue(row.findElements(By.xpath("//td")).get(AllAppsEnum.READ.getIndex())
                        .getText() != "", "Workflow Read information is empty.");
                Assert.assertTrue(row.findElements(By.xpath("//td")).get(AllAppsEnum.WRITE.getIndex())
                        .getText() != "", "Write information is empty.");
                Assert.assertTrue(row.findElements(By.xpath("//td")).get(AllAppsEnum.START_TIME.getIndex())
                        .getText() != "", "Start time information is empty.");
                Assert.assertTrue(row.findElements(By.xpath("//td")).get(AllAppsEnum.STATUS.getIndex())
                        .getText() != "", "Status information is empty.");
                Assert.assertTrue(row.findElements(By.xpath("//td")).get(AllAppsEnum.INSIGHTS.getIndex())
                        .getText() != "", "Status information is empty.");
                row.findElements(By.xpath("//td")).get(AllAppsEnum.INSIGHTS.getIndex()).click();
                break;
            }
        }
    }
}
