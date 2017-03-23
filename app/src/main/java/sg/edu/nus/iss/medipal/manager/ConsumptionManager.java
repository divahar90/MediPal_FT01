package sg.edu.nus.iss.medipal.manager;


import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;


import sg.edu.nus.iss.medipal.asynTask.AddConsumption;
import sg.edu.nus.iss.medipal.asynTask.DeleteConsumption;
import sg.edu.nus.iss.medipal.asynTask.ListConsumption;
import sg.edu.nus.iss.medipal.asynTask.UpdateConsumption;
import sg.edu.nus.iss.medipal.dao.ConsumptionDAO;
import sg.edu.nus.iss.medipal.pojo.Consumption;

public class ConsumptionManager {
    private Consumption consumption;
    private ConsumptionDAO consumptionDAO;
    private ArrayList<Consumption> consumptions;

    private AddConsumption addConsumption;
    private ListConsumption listConsumption;
    private UpdateConsumption updateConsumption;
    private DeleteConsumption deleteConsumption;


    private Context context;

    public ConsumptionManager(Consumption consumption, ConsumptionDAO consumptionDAO, Context context) {
        this.consumption = consumption;
        this.consumptionDAO = consumptionDAO;
        this.context = context;
    }

    public ConsumptionManager(int quantity,String data_time,Context context) {
        this.context = context;
        consumption = new Consumption(null,null,quantity,data_time);
        consumptionDAO = new ConsumptionDAO(context);
    }

    public ConsumptionManager(Context context) {

        this.context = context;
        consumptionDAO = new ConsumptionDAO(context);
    }

    public Consumption getConsumption(int id){

        Iterator<Consumption> i = consumptions.iterator();

        while(i.hasNext()){
            Consumption c = i.next();
            if( c.getId() == id)
            {
                return c;
            }
        }

        return null;
    }

    public ArrayList<Consumption> getConsumptions(Context context) {
        listConsumption = new ListConsumption(context);
        listConsumption.execute((Void)null);

        try{
            consumptions = listConsumption.get();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }

        if(consumptions == null){
            consumptions = new ArrayList<Consumption>();
        }

        return consumptions;
    }

    public Consumption addConsumption(int id, int medicine_id,int quantity,String ConsumedOn,Context context){

        Consumption consumption = new Consumption(id, medicine_id,quantity,ConsumedOn);

        addConsumption = new AddConsumption(context);
        addConsumption.execute(consumption);
        return consumption;

    }

    public Consumption updateConsumption(int id, int medicine_id, int quantity,String ConsumedOn,Context context){

        Consumption consumption = new Consumption(id,medicine_id, quantity,ConsumedOn);

        updateConsumption = new UpdateConsumption(context);
        updateConsumption.execute(consumption);

        return consumption;
    }

    public void deleteConsumption(int id,Context context){

        Consumption m = getConsumption(id);

        if(m != null)
        {
            deleteConsumption = new DeleteConsumption(context);
            deleteConsumption.execute(m);
        }

    }




}