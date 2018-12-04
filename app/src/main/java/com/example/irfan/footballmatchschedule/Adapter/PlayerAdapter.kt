package com.example.irfan.footballmatchschedule.Adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.irfan.footballmatchschedule.Model.TeamPlayer
import com.example.irfan.footballmatchschedule.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*
import org.jetbrains.anko.*

class PlayerAdapter(private val items: List<TeamPlayer>, private val listener: (TeamPlayer) -> Unit):
        RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PlayerUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageview: ImageView = itemView.find(ID_IMAGE)
        val textViewName: TextView = itemView.find(ID_NAME)
        val textViewPosition: TextView = itemView.find(ID_POSITION)

        fun bindItem(items: TeamPlayer, clickListener: (TeamPlayer)-> Unit){
            Picasso.get().load(items.strThumb).into(imageview)

            textViewName.text = items.strPlayer
            textViewPosition.text = items.strPosition

            itemView.setOnClickListener {
                clickListener(items)
            }
        }
    }

    companion object {
        val ID_IMAGE = 1
        val ID_NAME = 2
        val ID_POSITION = 3
    }

    class PlayerUI: AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                relativeLayout{
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)

                    imageView {
                        id = ID_IMAGE
                        setImageResource(R.drawable.ic_event)
                    }.lparams{
                        width = dip(80)
                        height = dip(80)
                    }

                    textView {
                        id = ID_NAME
                        textColor = Color.BLACK
                        text = "name"
                        right = ID_IMAGE
                    }.lparams {
                        margin = dip(10)
                        rightOf(ID_IMAGE)
                    }

                    textView {
                        id = ID_POSITION
                        textColor = Color.BLACK
                        text = "position"
                    }.lparams {
                        margin = dip(10)
                        alignParentRight()
                    }
                }
            }
        }

    }

}