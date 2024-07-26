package crop_prediction;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Main extends JFrame {
    JLabel timer;
    JLabel prediction;
    JLabel fertilizer_prediction;
    JLabel nitrogen;
    JLabel phosphorous;
    JLabel potassium;
    JLabel temperature;
    JLabel humidity;
    JLabel ph;
    JLabel rainfall;

    public Main() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        timer = new JLabel();
        prediction = new JLabel("The most suitable crop is: ");
        fertilizer_prediction = new JLabel("The most suitable fertilizer is: ");
        nitrogen = new JLabel();
        phosphorous = new JLabel();
        potassium = new JLabel();
        temperature = new JLabel();
        humidity = new JLabel();
        ph = new JLabel();
        rainfall = new JLabel();

        setupLabelsStyle(timer, 100, 120, 500, 20);
        setupLabelsStyle(prediction, 100, 150, 500, 20);
        setupLabelsStyle(fertilizer_prediction, 100, 180, 500, 30);
        setupLabelsStyle(nitrogen, 200, 250, 500, 30);
        setupLabelsStyle(phosphorous, 200, 280, 500, 30);
        setupLabelsStyle(potassium, 200, 310, 500, 30);
        setupLabelsStyle(temperature, 200, 340, 500, 30);
        setupLabelsStyle(humidity, 200, 370, 500, 30);
        // setupLabelsStyle(ph, 200, 400, 60, 30); // Uncomment this line if you want to use the 'ph' label
        setupLabelsStyle(rainfall, 200, 400, 500, 30);

        setSize(600, 600);
        setLayout(null);
        setVisible(true);
    }

    private void setupLabelsStyle(JLabel label, int x, int y, int width, int height) {
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Arial", Font.PLAIN, 14)); // You can adjust the font size and style
        add(label);
    }

    public static void main(String[] args) {
        Main myobj = new Main();
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(() -> myobj.p(), 0, 10, TimeUnit.SECONDS);
    }

    public void p() {
        SendToModel obj = new SendToModel();

        try {
            obj.predict();
            if (SendToModel.N.equals("0")) {
                prediction.setText("Waiting for Readings......");
                fertilizer_prediction.setText("Waiting for Readings......");
            } else {
                prediction.setText("The most suitable crop is: " + SendToModel.prediction);
                fertilizer_prediction.setText("The most suitable fertilizer is: " + SendToModel.fertilizer_prediction);
            }
            nitrogen.setText("Nitrogen: " + SendToModel.N+" mg/kg");
            phosphorous.setText("Phosphorous: " + SendToModel.P+" mg/kg");
            potassium.setText("Potassium: " + SendToModel.K+" mg/kg");
            temperature.setText("Temperature: " + SendToModel.temperature + "°C");
            humidity.setText("Humidity: " + SendToModel.humidity + "%");
            ph.setText("PH: " + SendToModel.ph);
            rainfall.setText("Rainfall: " + SendToModel.rainfall);
            timer.setText("Entry id: " + SendToModel.entry_id);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
