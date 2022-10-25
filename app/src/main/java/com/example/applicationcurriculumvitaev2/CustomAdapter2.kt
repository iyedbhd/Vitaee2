package com.example.applicationcurriculumvitaev2
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationcurriculumvitaev2.data.Education
import com.example.applicationcurriculumvitaev2.databinding.CardViewDesign2Binding
import com.example.applicationcurriculumvitaev2.databinding.CardViewDesignBinding

class CustomAdapter2(private val mList: List<Education>) : RecyclerView.Adapter<CustomAdapter2.ViewHolder>() {

    var onItemClick: ((Education) -> Unit)? = null
    private lateinit var binding: CardViewDesign2Binding;

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val inflator = LayoutInflater.from(parent.context);
        //val view = inflator.inflate(R.layout.card_view_design, parent, false);
        binding = CardViewDesign2Binding.inflate(inflator, parent, false);
        return ViewHolder(binding.root)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        val imageBytes = Base64.decode(ItemsViewModel.pic, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        binding.imageview.setImageBitmap(decodedImage)


        // sets the text to the textview from our itemHolder class

        binding.dateTxt.setText(ItemsViewModel.startDate);
        binding.titleTxt.setText(ItemsViewModel.companyName);
        binding.locationText.setText(ItemsViewModel.companyAddress);

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        /*val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(mList[adapterPosition])
            }
        }*/
    }
}
