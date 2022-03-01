package mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.mostrar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.eliminar_fragment.*
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.R
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.Variables.arregloDescuento
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.Variables.arregloNombre
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.Variables.arregloPrecio
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.Variables.indice
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class EliminarFragment : Fragment() {
    private var num=0
    companion object {
        fun newInstance() = EliminarFragment()
    }

    private lateinit var viewModel: EliminarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.eliminar_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {


        btnElim.setOnClickListener {
             num = txtNum.text.toString().toInt()
             (num-1..13).forEach() {
                 arregloNombre[num-1]= arregloNombre[num]
                 arregloPrecio[num-1]= arregloPrecio[num]
                 arregloDescuento[num-1]= arregloDescuento[num]
             }
             arregloNombre[14]=""
             arregloPrecio[14]=""
             arregloDescuento[14]=""
             Toast.makeText(activity,"Combo eliminado", Toast.LENGTH_LONG).show()
             txtNum.setText("")

             val combos = OutputStreamWriter(requireActivity().openFileOutput("combos.txt",0))
            indice--
             (0..indice-1).forEach(){
                 combos.write("${arregloNombre[it]},${arregloPrecio[it]},${arregloDescuento[it]}\n")
             }
             combos.flush()
             combos.close()
             llenarCombos()
         }
         llenarCombos()


        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EliminarViewModel::class.java)
        // TODO: Use the ViewModel
    }
    private fun llenarCombos(){
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
        if (indice ==0)
            txtTodo.setText("No hay combos")
        else{
            var aux=""
            (0..indice - 1).forEach() {
                aux+="${it+1}.- ${arregloNombre[it]} a ${arregloPrecio[it]} \n"
            }
            txtTodo.setText(aux)
        }
    }

}