package com.example.applicationcurriculumvitaev2
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationcurriculumvitaev2.data.Experience
import com.example.applicationcurriculumvitaev2.databinding.ActivityCareerBinding
import com.example.applicationcurriculumvitaev2.databinding.CardViewDesignBinding
class CustomAdapter(private var mList: List<Experience>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var onItemClick: ((Experience) -> Unit)? = null
    private lateinit var binding: CardViewDesignBinding;

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val inflator = LayoutInflater.from(parent.context);
        //val view = inflator.inflate(R.layout.card_view_design, parent, false);
        binding = CardViewDesignBinding.inflate(inflator, parent, false);
        return ViewHolder(binding.root)
    }

    public fun refreshView(mList: List<Experience>)
    {
        this.mList = mList;

    }
    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        val imageBytes = Base64.decode(ItemsViewModel.pic, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        binding.imageview.setImageBitmap(decodedImage)


        // sets the text to the textview from our itemHolder class

        binding.dateTxt.setText(ItemsViewModel.startDate);
        binding.dateText.setText(ItemsViewModel.endDate);
        binding.nameText.setText(ItemsViewModel.companyName);
        binding.descriptionText.setText(ItemsViewModel.companyName);
        binding.locationText.setText(ItemsViewModel.companyAddress);
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        /*val imageView: ImageView = itemView.findViewById(R.id.imageview)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(mList[adapterPosition])
            }
        }*/
    }
}
