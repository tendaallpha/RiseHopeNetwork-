package cm.genie6.risehope;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cm.genie6.risehope.controller.AccountController;
import cm.genie6.risehope.controller.ActivityController;
import cm.genie6.risehope.controller.ArticleController;
import cm.genie6.risehope.controller.ChildController;
import cm.genie6.risehope.controller.DefaultController;
import cm.genie6.risehope.controller.NecessityController;
import cm.genie6.risehope.controller.StatusController;

@SpringBootApplication
public class RiseHopeApplication {

	public static void main(String[] args) {
		File filearticleimages = new File(ArticleController.ARTICLESIMAGES);
		filearticleimages.mkdir();
		File fileProfile = new File(AccountController.PROFILEDIRECTORY);
		fileProfile.mkdir();
		File filegallery = new File(DefaultController.LOADGALLERY);
		filegallery.mkdir();
		File filechildrenprofile = new File(ChildController.CHILDRENPROFILE);
		filechildrenprofile.mkdir();
		File filestatusimg= new File(StatusController.STATUSIMAGES);
		filestatusimg.mkdir();
		File fileCenterImages= new File(AccountController.PROFILECENTER);
		fileCenterImages.mkdir();
		File fileNecessityImages= new File(NecessityController.NECESSITYIMAGES);
		fileNecessityImages.mkdir();
		File fileActivityImages= new File(ActivityController.ACTIVITYIMAGES);
		fileActivityImages.mkdir();
		System.out.println(filearticleimages.getAbsolutePath());
		
		SpringApplication.run(RiseHopeApplication.class, args);
	}
}
