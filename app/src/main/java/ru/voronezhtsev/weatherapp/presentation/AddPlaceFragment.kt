package ru.voronezhtsev.weatherapp.presentation

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_add_place.*
import ru.voronezhtsev.weatherapp.R
import ru.voronezhtsev.weatherapp.models.presentation.CityModel

private const val ARG_CITIES = "cities"

/**
 * Аргумент добавления нового места для отображения погоды
 *
 */
class AddPlaceFragment : Fragment() {
    private var cities: ArrayList<CityModel>? = null
    private var listener: OnPlaceClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cities = arguments?.getParcelableArrayList<CityModel>(ARG_CITIES)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_place, container, false)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (activity !is OnPlaceClickListener) {
            throw RuntimeException("Activity should implement OnPlaceClickListener")
        }
        listener = activity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val map = cities?.associateBy({ it.name }, { it.id })
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, map!!.keys.toTypedArray())
        cityInput.setAdapter(adapter);
        cityInput.setOnItemClickListener { parent, _, position, _ ->
            val cityId = map[parent.getItemAtPosition(position).toString()]
            listener?.onClick(cityId!!)
            requireActivity().onBackPressed()
        }
    }

    companion object {

        /**
         * @param cities Список городов
         * @return Экземпляр фрагмента
         */
        @JvmStatic
        fun newInstance(cities: List<CityModel>) =
                AddPlaceFragment().apply {
                    arguments = Bundle().apply {
                        putParcelableArrayList(ARG_CITIES, ArrayList(cities))
                    }
                }
    }
}
