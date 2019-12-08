package com.ashley_laptop.recipemine.view

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.ashley_laptop.recipemine.R
import com.ashley_laptop.recipemine.models.Search
import com.ashley_laptop.recipemine.viewmodel.ProfileViewModel
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response


class RecipeActivity : AppCompatActivity() {
    private lateinit var profileViewModel: ProfileViewModel
    private var user: String = ""
    private var isVeggies: Boolean = false
    private var isVegans: Boolean = false
    private var isGlutens: Boolean = false
    private var isDairys: Boolean = false
    private var intolerances: String = ""
    private var diets: String = ""

    private fun getUserName() = intent.extras?.get("username").toString().trim()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        user = getUserName()

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        profileViewModel.getUserProfileDairy("${user}Dairy").observe(this,
            androidx.lifecycle.Observer {dairyData(it)})
        profileViewModel.getUserProfileVeggie("${user}Veggie").observe(this,
            androidx.lifecycle.Observer {veggieData(it)})
        profileViewModel.getUserProfileVegan("${user}Vegan").observe(this,
            androidx.lifecycle.Observer {veganData(it)})
        profileViewModel.getUserProfileGluten("${user}Gluten").observe(this,
            androidx.lifecycle.Observer {glutenData(it)})

        val searchTask = asyncTaskSearch()
        val searchResults:Search? = searchTask.execute("query=dinner&number=10&diet=${diets}&intolerances=${intolerances}").get()
        Log.d("TEMP", searchResults?.mResults?.get(0)?.mTitle)

    }

    class asyncTaskSearch() : AsyncTask<String?, Void, Search?>() {
        override fun doInBackground(vararg params: String?): Search? {

            try {
                val client = OkHttpClient()

                val request: Request = Request.Builder()
                    .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search?" + params.get(0))
                    .get()
                    .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "526f7ebcf2msh61e773bb3c87effp12ca72jsn7c53ce0c2887")
                    .build()

                val response: Response = client.newCall(request).execute()

                val responseJSON:String = response.body().string()
//                Log.d("TEMP", responseJSON)

                val searchResults: Search = Gson().fromJson(responseJSON, Search::class.java)
                return searchResults
            }
            catch (ex:Exception) { }

            return null
        }

        override fun onPreExecute() { super.onPreExecute() }
        override fun onPostExecute(result: Search?) { super.onPostExecute(result) }
    }

    private fun dairyData(dairy: Boolean){
        isDairys = dairy
        if(isDairys){
            intolerances += "dairy, "
        }
    }

    private fun veggieData(veggie: Boolean){
        isVeggies = veggie
        if(isVeggies){
            diets += "vegetarian, "
        }
    }

    private fun veganData(vegan: Boolean){
        isVegans = vegan
        if(isVegans){
            diets += "vegan"
        }
    }

    private fun glutenData(gluten: Boolean){
        isGlutens = gluten
        if(isGlutens){
            intolerances += "gluten"
        }
    }
}
