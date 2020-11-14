package au.edu.unsw.infs3634.gamifiedlearning;

import java.util.ArrayList;

public class Module {

    private String moduleName, moduleDescription;
    private int moduleIcon;

    public Module(String moduleName, String moduleDescription, int moduleIcon){
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
        this.moduleIcon = moduleIcon;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public void setModuleDescription(String moduleDescription) {
        this.moduleDescription = moduleDescription;
    }

    public int getModuleIcon() {
        return moduleIcon;
    }

    public void setModuleIcon(int moduleIcon) {
        this.moduleIcon = moduleIcon;
    }

    public static ArrayList <Module> getModules() {
        ArrayList<Module> modules = new ArrayList<>();
        modules.add(new Module("Heart", "How to look after your heart health", R.drawable.heart));

        return modules;
    }


}
