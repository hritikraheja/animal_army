package com.example.animalarmy.modelclasses;

import com.example.animalarmy.R;

import java.util.Collections;
import java.util.LinkedList;

import javax.xml.namespace.QName;

public class AllOrganisations {
    private LinkedList<Organisation> allOrganisations = new LinkedList<>();

    public AllOrganisations(){
        Organisation pfa = new Organisation("People For Animals (PFA)", R.drawable.wildlife_sos ,"https://www.peopleforanimalsindia.org", "https://www.https://www.peopleforanimalsindia.org/donate.php");
        Organisation peta = new Organisation("PETA India", R.drawable.peta,"https://secure.petaindia.com/", "https://secure.petaindia.com/donate/");
        Organisation aau = new Organisation("Animal Aid Unlimited", R.drawable.animal_aid_unlimited,"https://www.animalaidunlimited.org", "https://www.animalaidunlimited.org/donations/");
        Organisation bCross = new Organisation("Blue Cross Of India", R.drawable.blue_cross, "https://bluecrossofindia.org", "https://bluecrossofindia.org/donate");
        Organisation bspca = new Organisation("The Bombay Society for Prevention of Cruelity on Animals", R.drawable.bspca, "https://bombayspca.org", "https://bombayspca.org/donations_reg_form.aspx");
        Organisation care = new Organisation("Charlie's Animal Rescue Center", R.drawable.care, "https://charlie-care.com", "https://charlies-care.com/help-us/#donate");
        Organisation cupa = new Organisation("Compassion Unlimited Plus Action", R.drawable.cupa, "https://cupabangalore.org","https://cupabangalore.org/other-ways-to-donate/");
        Organisation drf = new Organisation("Debasree Roy Foundation", R.drawable.debashree_roy_foundation, "https://debasreeroyfoundation.org","http://debasreeroyfoundation.org/donatenow.aspx");
        Organisation fiapo = new Organisation("Federation of Indian Animal Protection Organisations", R.drawable.fiapo, "https://www.fiapo.org", "https://www.fiapo.org/donation/");
        Organisation friendicoes = new Organisation("Friendicoes - SECA", R.drawable.friendicoes, "https://friendicoes.org","https://friendicoes.org/onlinedonation/");
        Organisation bgrd = new Organisation("Bhartiya Gau Raksha Dal", R.drawable.gaurakshadal, "https://bgrd.org","http://bgrd.org/donation-form/");
        Organisation his = new Organisation("Help In Suffering", R.drawable.help_in_suffering, "https://his-india.in", "http://www.his-india.in/donate-gifts.html");
        Organisation posh = new Organisation("POSH Foundation", R.drawable.posh_foundation, "https://poshfoundation.in", "https://www.poshfoundation.in/donate");
        Organisation raahat = new Organisation("Animal Raahat", R.drawable.raahat, "https://www.animalraahat.com", "https://support.animalrahat.com/page/17220/donate/");
        Organisation rpr = new Organisation("Red Paws Rescue", R.drawable.red_paws_rescue, "https://www.redpawsrescue.com","https://www.redpawsrescue.com/donate");
        Organisation sgacc = new Organisation("Sanjay Gandhi Animal Care Center", R.drawable.sgacc, "https://www.sanjaygandhianimalcarecenter.org", "http://sanjaygandhianimalcarecentre.org/donation.html");
        Organisation straw = new Organisation("Stray Relief And Animal Welfare (STRAW)", R.drawable.straw, "https://www.strawindia.org", "https://www.strawindia.org/donate.aspx");
        Organisation vspca = new Organisation("Vishaka's Society For Protection and Care of Animals", R.drawable.vspca, "https://vspca.org","https://vspca.org/donate/");
        Organisation wsos = new Organisation("Wildlife SOS", R.drawable.wildlife_sos, "https://wildlifesos.org", "https://give.wildlifesos.org/page/16138/donate/1");
        Organisation wti = new Organisation("Wildlife Trust Of India", R.drawable.wti, "https://www.wti.org.in", "https://www.wti.org.in/make-a-donation/");
        allOrganisations.add(pfa);
        allOrganisations.add(peta);
        allOrganisations.add(aau);
        allOrganisations.add(bCross);
        allOrganisations.add(bspca);
        allOrganisations.add(care);
        allOrganisations.add(cupa);
        allOrganisations.add(drf);
        allOrganisations.add(fiapo);
        allOrganisations.add(friendicoes);
        allOrganisations.add(bgrd);
        allOrganisations.add(his);
        allOrganisations.add(posh);
        allOrganisations.add(raahat);
        allOrganisations.add(rpr);
        allOrganisations.add(sgacc);
        allOrganisations.add(straw);
        allOrganisations.add(vspca);
        allOrganisations.add(wsos);
        allOrganisations.add(wti);
        Collections.sort(allOrganisations);
    }

    public LinkedList<Organisation> getListOfAllOrganisations(){
        return allOrganisations;
    }
}
