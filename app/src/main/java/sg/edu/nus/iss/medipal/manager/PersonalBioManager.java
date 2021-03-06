package sg.edu.nus.iss.medipal.manager;

import android.content.Context;

import java.util.Date;

import sg.edu.nus.iss.medipal.dao.PersonalBioDAO;
import sg.edu.nus.iss.medipal.pojo.PersonalBio;

/**
 * Created by Divahar on 3/17/2017.
 * Description: This manager class takes care of all the operations between Activity and DAO for personal Bio
 */

public class PersonalBioManager {

    private PersonalBioDAO personalBioDAO;
    PersonalBio personalBio;

    public long addpersonalBio(String nameStr, Date dobDt, String idNoStr, String addressStr,
                               String postalCodeStr, String heightStr, String bloodTypeStr, Context context) {
        long result;
        personalBio =
                new PersonalBio(nameStr, dobDt, idNoStr, addressStr,
                        Integer.valueOf(postalCodeStr), Integer.valueOf(heightStr), bloodTypeStr);
        personalBioDAO = new PersonalBioDAO(context);
        result =personalBioDAO.insert(personalBio);
        personalBioDAO.close();
        return result;
    }

    public PersonalBio getpersonalBio(Context context) {
        PersonalBio personalBio;
        personalBioDAO = new PersonalBioDAO(context);
        personalBio = personalBioDAO.retrieve();
        personalBioDAO.close();
        return personalBio;
    }

    public long updatepersonalBio(int id, String nameStr, Date dobDt, String idNoStr, String addressStr,
                                  String postalCodeStr, String heightStr, String bloodTypeStr, Context context) {
        long result;

        personalBio =
                new PersonalBio(id, nameStr, dobDt, idNoStr, addressStr,
                        Integer.valueOf(postalCodeStr), Integer.valueOf(heightStr), bloodTypeStr);
        personalBioDAO = new PersonalBioDAO(context);
        result = personalBioDAO.update(personalBio);
        personalBioDAO.close();
        return result;
    }
}
