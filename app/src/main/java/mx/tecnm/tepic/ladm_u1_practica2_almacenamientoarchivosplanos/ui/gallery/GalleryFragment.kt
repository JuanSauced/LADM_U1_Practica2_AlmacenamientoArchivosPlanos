package mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentGalleryBinding
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.Variables.arregloDescuento
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.Variables.arregloNombre
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.Variables.arregloPrecio
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.Variables.indice
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var combo= 0
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
            indice =0
        }
        if (indice==0)
        binding.txtCombos.setText("No hay combos")
        else{
            var aux=""
            (0..indice - 1).forEach() {
                aux+="${it+1}.- ${arregloNombre[it]} a ${arregloPrecio[it]} \n"
            }
            binding.txtCombos.setText(aux)
        }
        binding.btnAct.setOnClickListener {
            binding.txtCombos.setText("")
            combo= binding.txtNum.text.toString().toInt()
            binding.txtNom.setText(arregloNombre[combo-1])
            binding.txtPrec.setText(arregloPrecio[combo-1])
            binding.txtDesc.setText(arregloDescuento[combo-1])
        }
        binding.btnActualizar.setOnClickListener {
            arregloNombre[combo-1]= binding.txtNom.text.toString()
            arregloPrecio[combo-1]= binding.txtPrec.text.toString()
            arregloDescuento[combo-1]=binding.txtDesc.text.toString()
            Toast.makeText(activity,"Combo Actualizado", Toast.LENGTH_LONG).show()
            binding.txtNum.setText("")
            binding.txtNom.setText("")
            binding.txtPrec.setText("")
            binding.txtDesc.setText("")
            val ag = OutputStreamWriter(requireActivity().openFileOutput("combos.txt",0))
            var txt =""
            (0..indice-1).forEach(){
                ag.write("${arregloNombre[it]},${arregloPrecio[it]},${arregloDescuento[it]}\n")
                txt+="${it+1}.- ${arregloNombre[it]} a ${arregloPrecio[it]} \n"
            }
            ag.flush()
            ag.close()
            binding.txtCombos.setText(txt)
        }
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}