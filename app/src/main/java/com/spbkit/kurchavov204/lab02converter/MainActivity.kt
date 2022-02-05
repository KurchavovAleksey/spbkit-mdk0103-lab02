package com.spbkit.kurchavov204.lab02converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*


class MainActivity : AppCompatActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private var fromField: EditText? = null
    private var toText: TextView? = null  // Курчавов Алексей 204 группа СПБКИТ

    private var spFrom: Spinner? = null
    private var spTo: Spinner? = null

    private val distanceUnitsDict = mutableMapOf<String, Unit>()
    private val massUnitDict = mutableMapOf<String, Unit>()
    private val pressureUnitDict = mutableMapOf<String, Unit>()  // Курчавов Алексей 204 группа СПБКИТ
    private var activeUnitDict = mutableMapOf<String, Unit>()

    private var arrayAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fromField = findViewById(R.id.EditText_from)  // Курчавов Алексей 204 группа СПБКИТ
        toText = findViewById(R.id.textView_result)

        spFrom = findViewById(R.id.spinner_from)
        spTo = findViewById(R.id.spinner_to)

        distanceUnitsDict["mm"] = Unit("mm", 1000f)  // absolute is meters
        distanceUnitsDict["cm"] = Unit("cm", 100f)
        distanceUnitsDict["dm"] = Unit("dm", 10f)  // Курчавов Алексей 204 группа СПБКИТ
        distanceUnitsDict["m"] = Unit("m", 1f)
        distanceUnitsDict["km"] = Unit("km", 0.001f)

        massUnitDict["g"] = Unit("g", 1000f)  // absolute is kilograms
        massUnitDict["kg"] = Unit("kg", 1f)
        massUnitDict["cwt"] = Unit("cwt", 0.01f)
        massUnitDict["ton"] = Unit("ton", 0.001f)

        pressureUnitDict["pa"] = Unit("pa", 1f)  // absolute if pascals
        pressureUnitDict["bar"] = Unit("bar", 10000f)
        pressureUnitDict["atm"] = Unit("atm", 101325f)
        pressureUnitDict["mm hg"] = Unit("mm hg", 133.322f)  // Курчавов Алексей 204 группа СПБКИТ
        pressureUnitDict["psi"] = Unit("psi", 6894.76f)

        arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        spFrom?.adapter = arrayAdapter  // Курчавов Алексей 204 группа СПБКИТ
        spTo?.adapter = arrayAdapter

        activateDistance()

        findViewById<Button>(R.id.button_convert).setOnClickListener(this)
        findViewById<RadioGroup>(R.id.unit_type).setOnCheckedChangeListener(this)

    }

    override fun onClick(view: View?) {
        try {

            val from: Float = fromField?.text.toString().toFloat()  // Курчавов Алексей 204 группа СПБКИТ
            val fromUnits: String = spFrom?.selectedItem.toString()
            val targetUnits: String = spTo?.selectedItem.toString()

            val valueAbsolute: Float = activeUnitDict[fromUnits]!!.unitToAbsolute(from)
            val targetValue: Float = activeUnitDict[targetUnits]!!.absoluteToUnit(valueAbsolute)

            setResult(targetValue.toString())

        } catch (e: NumberFormatException) {
            setResult(getString(R.string.text_error_incorrect_value))  // Курчавов Алексей 204 группа СПБКИТ
            return
        }

    }

    override fun onCheckedChanged(radioGroup: RadioGroup?, buttonId: Int) {
        when(buttonId) {
            R.id.radioButton_distance -> activateDistance()
            R.id.radioButton_pressure -> activatePressure()
            R.id.radioButton_mass -> activateMass()
        }
    }

    private fun setResult(text: String){
        toText?.text = text
    }  // Курчавов Алексей 204 группа СПБКИТ

    private fun setArrayAdapter(values: Collection<String>) {
        arrayAdapter?.clear()
        arrayAdapter?.addAll(values)

        setResult("")
        fromField?.setText("")
    }

    private fun activateDistance() {
        setArrayAdapter(distanceUnitsDict.keys)
        activeUnitDict = distanceUnitsDict  // Курчавов Алексей 204 группа СПБКИТ
    }

    private fun activatePressure() {
        setArrayAdapter(pressureUnitDict.keys)
        activeUnitDict = pressureUnitDict
    }

    private fun activateMass() {
        setArrayAdapter(massUnitDict.keys)  // Курчавов Алексей 204 группа СПБКИТ
        activeUnitDict = massUnitDict
    }

}