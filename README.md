# Crop Prediction and Fertilizer Recommendation System using IoT & AI

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Methodology](#methodology)
- [System Components](#system-components)
- [Circuit Diagram](#circuit-diagram)
- [Results](#results)
- [Advantages](#advantages)
- [Future Scope](#future-scope)
- [Conclusion](#conclusion)
- [References](#references)

## Introduction
Agriculture is a significant economic sector in every country. Our project, the Crop Prediction and Fertilizer Recommendation System, aims to enhance agricultural efficiency and sustainability. By utilizing IoT sensors and AI algorithms, this system monitors soil nutrient levels and predicts optimal crops, providing real-time data to help farmers make informed decisions regarding fertilization, irrigation, and harvesting.

## Features
- **Real-time Soil Monitoring**: Measures soil nutrient content (NPK), moisture, and temperature.
- **Crop Prediction**: Uses machine learning algorithms to predict suitable crops based on soil conditions.
- **Fertilizer Recommendation**: Suggests appropriate fertilizers to optimize crop yield.
- **Cost-effective**: Reduces the need for manual soil sampling and laboratory testing.
- **Sustainable Agriculture**: Promotes efficient use of fertilizers and reduces environmental impact.

## Methodology
1. **Data Collection**: Deploy IoT sensors in agricultural fields to gather data on soil moisture, temperature, and nutrient content.
2. **Data Pre-processing**: Clean and normalize the data to eliminate noise and handle missing values.
3. **Feature Engineering**: Extract relevant features that impact crop growth and nutrient levels.
4. **Model Selection**: Evaluate various machine learning algorithms and select the most suitable one for crop prediction.
5. **Model Training**: Train the chosen model using the pre-processed data.
6. **Model Evaluation**: Assess the model's accuracy and performance using a testing dataset.
7. **Deployment**: Implement the trained model for real-time crop prediction and fertilizer recommendation.

   <img src="https://github.com/Prathmesh-Deshmukh/AgriPragati_Project/blob/main/Img/methodology.png" width="600" height="400" alt="Methodology">


## System Components
- **Sensors**:
  - Capacitive Soil Moisture Sensor
  - DS18B20 Waterproof Temperature Sensor
  - Soil NPK Sensor
- **Microcontroller**: Arduino Nano
- **Communication Module**: ESP32 WiFi Module
- **Server**: ThingSpeak for data monitoring and analysis

## Circuit Diagram
Circuit Diagram <br>
   <img src="https://github.com/Prathmesh-Deshmukh/AgriPragati_Project/blob/main/Img/circuit%20diagram.png" width="600" height="400" alt="Methodology">

Main Circuit <br>
   <img src="https://github.com/Prathmesh-Deshmukh/AgriPragati_Project/blob/main/Img/main_circuit_digram.png" width="600" height="400" alt="Methodology">

## Results
Data visualizations on the ThingSpeak server:
- Nitrogen and Phosphorus levels
- Potassium and Temperature levels
- Soil Moisture and pH levels

Output <br>
<img src="https://github.com/Prathmesh-Deshmukh/AgriPragati_Project/blob/main/Img/Applet%20Output%20SS.png" width="400" height="400" alt="Methodology">

## Advantages
- **Real-time Data**: Provides farmers with immediate information on soil nutrient levels and crop predictions.
- **Improved Crop Yields**: Enhances agricultural productivity and reduces the need for chemical fertilizers.
- **Sustainability**: Supports eco-friendly farming practices and minimizes environmental harm.
- **Cost-effective**: Lowers costs by eliminating the need for frequent manual soil sampling and lab tests.
- **User-friendly**: Easy access via mobile and web interfaces, suitable for users with limited technical knowledge.

## Future Scope
- Extend the system to monitor and track crop growth continuously.
- Implement preventive measures against crop diseases and adverse climatic conditions.
- Enhance the model to include more environmental and crop-specific variables for improved accuracy.

## Conclusion
The Crop Prediction and Fertilizer Recommendation System is a crucial development for modern agriculture. By leveraging IoT and AI technologies, it ensures sustainable farming practices, improves crop yields, and promotes environmental health.

## References
- [Smart Crop Prediction using IoT and Machine Learning - IJERT](https://example.com)
- [Smart Agriculture Based on IoT and Machine Learning - IEEE](https://example.com)
- [Soil Monitoring and Recommendation System - SSRN](https://example.com)
- [Crop Prediction using Machine Learning - IEEE](https://example.com)
- [IoT based Soil Nutrients Analysis and Monitoring System for Smart Agriculture - IEEE](https://example.com)
