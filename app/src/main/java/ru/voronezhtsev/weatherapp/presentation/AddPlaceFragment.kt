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
    private var listener: OnAddPlaceClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cities = arguments?.getParcelableArrayList(ARG_CITIES)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_place, container, false)
    }

    fun setOnAddPlaceClickListener(listener: OnAddPlaceClickListener) {
        this.listener = listener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val map = cities?.associateBy({ it.name }, { it.id })
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, map!!.keys.toTypedArray())
        cityInput.setAdapter(adapter);
        cityInput.setOnItemClickListener { parent, _, position, _ ->
            val cityId = map[parent.getItemAtPosition(position).toString()]
            listener?.onAddPlace(cityId!!)
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
    interface OnAddPlaceClickListener {
        fun onAddPlace(city: Long)
    }
}
