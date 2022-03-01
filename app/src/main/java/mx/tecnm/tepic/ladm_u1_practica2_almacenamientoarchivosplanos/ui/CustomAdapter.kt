package mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.R
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.Variables.arregloDescuento
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.Variables.arregloNombre
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.Variables.arregloPrecio
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.Variables.indice
import java.io.InputStreamReader

class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    val images = intArrayOf(
        R.drawable.burguer,
        R.drawable.burguer,
        R.drawable.burguer,
        R.drawable.burguer,
        R.drawable.burguer,
        R.drawable.burguer,
        R.drawable.burguer,
        R.drawable.burguer,
        R.drawable.burguer,
        R.drawable.burguer,
        R.drawable.burguer,
        R.drawable.burguer,
        R.drawable.burguer,
        R.drawable.burguer,
        R.drawable.burguer
    )
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout,viewGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

        viewHolder.itemTitle.text = arregloNombre[i]
        viewHolder.itemDetail.text ="Precio: ${arregloPrecio[i]} $"
        viewHolder.itemDesc.text="Descuento: ${arregloDescuento[i]} $"
        viewHolder.itemImage.setImageResource(images[i])
    }

    override fun getItemCount(): Int {

        return indice
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView
        var itemDesc: TextView
        init{
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detal)
            itemDesc = itemView.findViewById(R.id.item_desc)
        }
    }
}
