package com.example.apilab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apilab.R
import com.example.apilab.model.Animal
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.example.apilab.model.Characteristics



class AnimalAdapter(private val animalList: List<Animal>): RecyclerView.Adapter<AnimalAdapter.ViewHolder>() {

    private var filteredAnimal: List<Animal> = animalList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalAdapter.ViewHolder {
        val itemAnimalView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_animal, parent, false)


        return ViewHolder(itemAnimalView)
    }

    fun filter(query: String){
        val newList = if (query.isEmpty()) {
            animalList
        } else {
            filteredAnimal.filter { it.name?.contains(query, ignoreCase = true) == true
                    || it.taxonomy.scientificName?.contains(query, ignoreCase = true) == true}
        }
        submitList(newList)
    }

    fun submitList(newList: List<Animal>){
        val diffCallback = AnimalUtil(filteredAnimal, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        filteredAnimal = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: AnimalAdapter.ViewHolder, position: Int) {
        val animalViewModel = filteredAnimal[position]

        holder.animalName.text = animalViewModel.name
        holder.animalScientificName.text = animalViewModel.taxonomy.scientificName
        holder.animalLocations.text = animalViewModel.locations.joinToString(", ")
        holder.animalCharacteristics.text = formatCharacteristics(animalViewModel.characteristics)

        println("filtereddd ${filteredAnimal}")
    }

    override fun getItemCount(): Int {
        return filteredAnimal.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val animalName: TextView = itemView.findViewById(R.id.animalName)
        val animalScientificName: TextView = itemView.findViewById(R.id.animalScientificName)
        val animalLocations: TextView = itemView.findViewById(R.id.animalLocations)
        val animalCharacteristics: TextView = itemView.findViewById(R.id.animalCharacteristics)
    }

    private fun formatCharacteristics(characteristics: Characteristics): String {
        val sb = StringBuilder()
        sb.append("Prey: ${characteristics.prey}\n")
        sb.append("Name of Young: ${characteristics.nameOfYoung}\n")
        sb.append("Group Behavior: ${characteristics.groupBehavior}\n")

        return sb.toString()
    }
}

