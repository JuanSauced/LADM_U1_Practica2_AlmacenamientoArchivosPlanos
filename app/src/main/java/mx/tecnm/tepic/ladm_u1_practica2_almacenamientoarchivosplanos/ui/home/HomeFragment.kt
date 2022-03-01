package mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentHomeBinding
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.MainActivity
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.Variables.arregloDescuento
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.Variables.arregloNombre
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.Variables.arregloPrecio
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.Variables.indice

import java.io.InputStreamReader
import java.io.OutputStreamWriter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
    try {
        var aux = InputStreamReader(requireActivity().openFileInput("combos.txt"))
        var ind = aux.readLines()
        indice = ind.size
        if (indice > 0)
            (0..indice - 1).forEach() {
                var split = ind[it].split(",")
                arregloNombre[it] = split[0]
                arregloPrecio[it] = split[1]
                arregloDescuento[it] = split[2]
            }
    }catch (e:java.lang.Exception){
        indice=0
    }

        binding.btnAgregar.setOnClickListener {
            guardarArchivos()
            indice++
        }

        return root
    }

    private fun guardarArchivos(){
        try{

            arregloNombre[indice] = binding.txtNC.text.toString()
            arregloPrecio[indice] = binding.txtPrecio.text.toString()
            arregloDescuento[indice] = binding.txtDesc.text.toString()

            val combos = OutputStreamWriter(requireActivity().openFileOutput("combos.txt",0))

            (0..indice).forEach(){
                combos.write("${arregloNombre[it]},${arregloPrecio[it]},${arregloDescuento[it]}\n")

            }
            combos.flush()
            combos.close()

            binding.txtNC.setText("")
            binding.txtPrecio.setText("")
            binding.txtDesc.setText("")
            Toast.makeText(activity,"Combo guardado",Toast.LENGTH_LONG).show()

        }catch (e:Exception){

            AlertDialog.Builder((requireContext()))
                .setMessage(e.message).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}