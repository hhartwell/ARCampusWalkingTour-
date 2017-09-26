/* 
*   Pedometer
*   Copyright (c) 2017 Yusuf Olokoba
*/

namespace PedometerU.Tests {

    using UnityEngine;
    using UnityEngine.UI;

    public class StepCounter : MonoBehaviour {

        public Text stepText, distanceText;
        public string myStr = "myStr init";
        public string myStr2 = "str2 init";
        public string mySteps = "mySteps init";
        public int mystepct = 0;
        private Pedometer pedometer;

        //may need to get gps permission use
        //see https://github.com/NathanaelA/nativescript-permissions

        private void Start () {
            myStr = "started";
            // Create a new pedometer
            pedometer = new Pedometer(OnStep);
            // Reset UI
            OnStep(0, 0);
        }

        private void OnStep (int steps, double distance) {
            // Display the values // Distance in feet
            mystepct++;
            myStr2 = "in OnStep";
            stepText.text = "Steps: " + steps.ToString();
            mySteps = steps.ToString();
            distanceText.text = "Distance: " + (distance * 3.28084).ToString("F2") + " ft";
        }

        //RB added OnGUI
        void OnGUI()
        {
            foreach (Touch touch in Input.touches)
            {
                GUI.Label(new Rect(0, 0,420, 200), "in GUI " + myStr + " " +  myStr2 + " " + mySteps  + " ct= " + mystepct.ToString());
            }
        }

        private void OnDisable () {
            // Release the pedometer
            pedometer.Dispose();
            pedometer = null;
        }
    }
}
