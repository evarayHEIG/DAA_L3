package ch.heigvd.iict.daa.template

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class PlaceholderSpinnerAdapter<T>(context: Context, resource: Int, objects: List<T>) :
    ArrayAdapter<T>(context, resource, objects) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

        if(position == 0) {

            return View(context).also{it.visibility = View.GONE}

        }else{
            return super.getDropDownView(position, null, parent)
        }
    }


    override fun isEnabled(position: Int): Boolean {

        return position != 0
    }

}