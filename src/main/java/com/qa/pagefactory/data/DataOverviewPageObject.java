package com.qa.pagefactory.data;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DataOverviewPageObject {
    @FindBy(xpath = "//a[contains(@href,'data/overview')]/span[contains(text(),\"Data\")]")
    public WebElement dataTab;

    @FindBy(xpath = "//a[contains(@href,'data/tables')]/span[contains(text(),\"Tables\")]")
    public WebElement dataTablesTab;

    @FindBy(xpath = "(//label[contains(text(),'Workspace')]/following-sibling::span)[1]//span[contains(@class,'select2-selection__arrow')]")
    public WebElement workspaceDropdown;

    @FindBy(css = "input.global-app-search")
    public WebElement globalSearchBox;

    public String filterByValues = "//li[contains(text(),'%s')]";

    @FindBy(xpath = "(//label[contains(text(),'Metastore')]/following-sibling::span)[1]")
    public WebElement metastoreParentElement;

    @FindBy(xpath = "//span[@class='select2-results']//li")
    public List<WebElement> selectType;

    @FindBy(xpath = "//li[contains(@class,'select2-results__option')]")
    public List<WebElement> dropdownValues;

    @FindBy(xpath = "//table[contains(@class,'component-data-tables')]/thead/tr/th")
    public List<WebElement> tableHeadings;

    @FindBy(xpath = "//table[contains(@class,'component-data-tables')]/tbody/tr")
    public List<WebElement> tableRows;

    @FindBy(xpath = "//p[contains(text(),'No table KPIs available.')]")
    public List<WebElement> noTableKPIsElements;

    @FindBy(xpath = "//h2[contains(text(),'Tables KPIs')]/following-sibling::div[1]" +
            "//div[contains(@class,'kpi-parent')]/div/div/h2")
    public List<WebElement> tablesKPIsLastDayValues;

    @FindBy(xpath = "//div[@class='row no-gutters container-fluid']")
    public List<WebElement> tableKPIsElements;

    @FindBy(xpath = "//p[contains(text(),'No partition KPIs available.')]")
    public List<WebElement> noPartitionKPIsElements;

    @FindBy(xpath = "//h2[contains(text(),'Tables KPIs')]/following-sibling::div[1]//" +
            "div[contains(@class,'kpi-parent')]/div[contains(@class,'title-val')]")
    public List<WebElement> tablesKPIsLastDayTitles;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public DataOverviewPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

