package apc.entjava.agora;

public class Budget {
    private String budget_materials;
    private String budget_operations;
    private String budget_management;
    private String budget_labor;
    private String budget_misc;

    public Budget(String budget_materials, String budget_operations, String budget_management, String budget_labor, String budget_misc) {
        this.budget_materials =  budget_materials;
        this.budget_operations =  budget_operations;
        this.budget_management =  budget_management;
        this.budget_labor =  budget_labor;
        this.budget_misc =  budget_misc;
    }

    public String getBudget_materials() {
        return budget_materials;
    }

    public void setBudget_materials(String budget_materials) {
        this.budget_materials = budget_materials;
    }

    public String getBudget_operations() {
        return budget_operations;
    }

    public void setBudget_operations(String budget_operations) {
        this.budget_operations = budget_operations;
    }

    public String getBudget_management() {
        return budget_management;
    }

    public void setBudget_management(String budget_management) {
        this.budget_management = budget_management;
    }

    public String getBudget_labor() {
        return budget_labor;
    }

    public void setBudget_labor(String budget_labor) {
        this.budget_labor = budget_labor;
    }

    public String getBudget_misc() {
        return budget_misc;
    }

    public void setBudget_misc(String budget_misc) {
        this.budget_misc = budget_misc;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "budget_materials='" + budget_materials + '\'' +
                ", budget_operations='" + budget_operations + '\'' +
                ", budget_management='" + budget_management + '\'' +
                ", budget_labor='" + budget_labor + '\'' +
                ", budget_misc='" + budget_misc + '\'' +
                '}';
    }
}
