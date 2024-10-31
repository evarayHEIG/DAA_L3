package ch.heigvd.iict.daa.template

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

/**
 * A custom adapter for a spinner that supports a placeholder item as the first item,
 * which is hidden in the dropdown menu and disabled from selection.
 *
 * @author Rachel Tranchida
 * @author Massimo Stefani
 * @author Eva Ray
 *
 * @param T the type of items in the spinner.
 * @param context the current context.
 * @param resource the layout resource ID for a single item.
 * @param objects the list of items in the spinner.
 */
class PlaceholderSpinnerAdapter<T>(context: Context, resource: Int, objects: List<T>) :
    ArrayAdapter<T>(context, resource, objects) {

    /**
     * Returns the view for the dropdown menu. The first item (position 0) is treated as
     * a placeholder, so it is hidden by setting the visibility to GONE.
     *
     * @param position the position of the item within the adapter's data set.
     * @param convertView the recycled view to populate.
     * @param parent the parent view that this view will be attached to.
     * @return the View for the dropdown item.
     */
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

        if (position == 0) {

            return View(context).also { it.visibility = View.GONE }

        } else {
            return super.getDropDownView(position, null, parent)
        }
    }

    /**
     * Determines whether the item at the specified position is enabled or not.
     * The first item (position 0) is treated as a placeholder and is disabled.
     *
     * @param position the position of the item within the adapter's data set.
     * @return true if the item is enabled, false if it is disabled.
     */
    override fun isEnabled(position: Int): Boolean {

        return position != 0
    }

}