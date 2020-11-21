package au.edu.unsw.infs3634.gamifiedlearning;

import java.util.ArrayList;

public class Module {

    private String moduleName, moduleDescription, moduleData;
    private int moduleIcon;
    public Module(String moduleName, String moduleDescription, int moduleIcon, String moduleData){
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
        this.moduleIcon = moduleIcon;
        this.moduleData = moduleData;

    }

    public String getModuleName() {return moduleName; }

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

    public String getModuleData(){return moduleData;}

    public void setModuleData(String moduleData){this.moduleData = moduleData;}


    public static ArrayList <Module> getModules() {
        ArrayList<Module> modules = new ArrayList<>();
        modules.add(new Module("Mental", "How to look after your mental health", R.drawable.mentalhealth,"Mental health is critical for everyone to enjoy and succeed at life, study and work. There are many ways to take care of your body these can include:\n" +
                "Keeping active and exercises and this helps improve modes with the release of Brain-derived neurotrophic factor (BDNF).\n" +
                "Drinking enough water which is about 2 litters a day\n" +
                "Eating quality meals\n" +
                "Getting good sleep is also critical as you brain assists our body healing itself from stressors throughout the day, it is recommended to get eight hours of sleep a day.\n" +
                "https://www.youtube.com/watch?v=lJ9PGJu_pOk&feature=emb_title&ab_channel=UNSW \n"));

        modules.add(new Module("Muscle", "How to look after your muscular health", R.drawable.muscle,"As well as the benefits of keeping fit and healthy, eating well and keeping on the move improves brain function and can reduce stress and sickness. Did you know that you have more than 600 muscles in your body! It is important to warm up and cool down before you use your muscles in a workout, this though take about 10-20 minutes before and after allowing your muscles to prepare and recover better. Additional stretching if super beneficial as it reduces muscle tears, and each stretch s should be for 20 seconds. The best time to stretch is after you warm up but before you go into your exercise.\n" +
                "https://www.youtube.com/watch?v=cBRmIfW867g&ab_channel=CincinnatiChildren%27s\n"));

        modules.add(new Module("Heart", "How to look after your heart health", R.drawable.heart,"The heart is a part of the circulatory system, it is made up of atria, ventricles, valves, arteries and veins. Its carries oxygen and other nutrients to all the bodies organs to stay healthy and work properly. It is a muscle itself, to maintain heart health it is important that you keep active as it needs to get strengthened as well. Additionally, losing weight and having a healthier diet is better for you heart and reducing your cholesterol can help as it narrows your arteries making your heart work harder and increasing your risk of heart disease.\n" +
                "https://www.youtube.com/watch?v=dHGI9O6m3B0&ab_channel=HeartFoundation\n"));

/*<<<<<<< Updated upstream

=======
        *//*modules.add(new Module("Teath", "How to look after your teath health", R.drawable.heart,"1 in 25 people over 15 years old have not natural teeth left. It is important to brush your teeth thoroughly twice a day and floss daily as good dental health can prevent gum disease, tooth decay, reduce tooth sensitivity and bad breath. Additionally, drinking matter between meals and seeing your dentist at least yearly for a clean, limiting sugary drink and coffee are more important ways to make sure that you have good dental health. And if you a snacker you may want to brush your teeth more regularly as bacteria can start forming plaque on teeth just 20 minutes after eating!\n" +
                "https://www.youtube.com/watch?v=4WWtnBH_ZQQ&feature=emb_title&ab_channel=UICCollegeofDentistry \n"));*//*
>>>>>>> Stashed changes*/
        return modules;
    }


}
