package com.qa.validations;

import com.qa.enums.WorkflowEnums;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class Workflow {

  public static void validateValueExistInTable(List<WebElement> tableRows) {
    for (WebElement row: tableRows) {
      if (row.findElements(By.xpath("//td")).get(WorkflowEnums.WORKFLOW_NAME.getIndex())
        .getText().trim().equals("SparkFileCopy")) {
        Assert.assertTrue(row.findElements(By.xpath("//td")).get(WorkflowEnums.USER.getIndex())
          .getText() != "", "User information is empty.");
        Assert.assertTrue(row.findElements(By.xpath("//td")).get(WorkflowEnums.TYPE.getIndex())
          .getText() != "", "Type information is empty.");
        Assert.assertTrue(row.findElements(By.xpath("//td")).get(WorkflowEnums.CLUSTER_ID.getIndex())
          .getText() != "", "Cluster information is empty.");
        Assert.assertTrue(row.findElements(By.xpath("//td")).get(WorkflowEnums.WORKFLOW_NAME.getIndex())
          .getText() != "", "Workflow Name is empty.");
        Assert.assertTrue(row.findElements(By.xpath("//td")).get(WorkflowEnums.DURATION.getIndex())
          .getText() != "", "Workflow Duration information is empty.");
        Assert.assertTrue(row.findElements(By.xpath("//td")).get(WorkflowEnums.READ.getIndex())
          .getText() != "", "Workflow Read information is empty.");
        Assert.assertTrue(row.findElements(By.xpath("//td")).get(WorkflowEnums.WRITE.getIndex())
          .getText() != "", "Write information is empty.");
        Assert.assertTrue(row.findElements(By.xpath("//td")).get(WorkflowEnums.START_TIME.getIndex())
          .getText() != "", "Start time information is empty.");
        Assert.assertTrue(row.findElements(By.xpath("//td")).get(WorkflowEnums.STATUS.getIndex())
          .getText() != "", "Status information is empty.");
        Assert.assertTrue(row.findElements(By.xpath("//td")).get(WorkflowEnums.WORKFLOW_TRENDS.getIndex())
          .getText() != "", "Workflow Trend information is empty.");
        Assert.assertTrue(row.findElements(By.xpath("//td")).get(WorkflowEnums.STATUS.getIndex())
          .getText() != "", "Status information is empty.");
        row.findElements(By.xpath("//td")).get(WorkflowEnums.WORKFLOW_NAME.getIndex()).click();
        break;
      }
    }
  }
}
