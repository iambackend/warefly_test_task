package com.example.ouser.wareflytest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ImageView dishImageView = (ImageView) findViewById(R.id.imageViewCollapsing);
        Glide.with(this).load("https://chocolatecoveredkatie.com/wp-content/uploads/homemade-Frappuccino.jpg").into(dishImageView);

        TextView recipeNameTextView = (TextView) findViewById(R.id.productName);
        recipeNameTextView.setText("· Фраппучино ·");

        RecyclerView ingredientsList = (RecyclerView) findViewById(R.id.ingredientsList);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        ingredientsList.setHasFixedSize(true);

        // specify an adapter (see also next example)
        Ingredient[] myDataset = {new Ingredient("Молоко 1,5-2%", "200 мл"),
                new Ingredient("Взбитые сливки", "1 ст. л."),
                new Ingredient("Карамельный сироп", "3 ч. л."),
                new Ingredient("Кофе Jacobs Monarch", "1 чашечка"),
                new Ingredient("Лёд", "2-3 кубика")};
        IngredientAdapter ingredientAdapter = new IngredientAdapter(myDataset);
        ingredientsList.setAdapter(ingredientAdapter);

        LinearLayout addToShoplist = findViewById(R.id.addToShopList);
        addToShoplist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Ну нажал ты, и что? Чего тебе надо то?", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView imagesList = (RecyclerView) findViewById(R.id.imagesList);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        imagesList.setHasFixedSize(true);

        // specify an adapter (see also next example)

        String[] imageSet = {
                "https://www.bbcgoodfood.com/sites/default/files/recipe-collections/collection-image/2013/05/sticky-pork-lettuce-wraps_1.jpg",
                "https://cdn-image.realsimple.com/sites/default/files/styles/medium_2x/public/1522180417/cooking-one-woman.jpg?itok=eiqsBRYm",
                "https://d3atagt0rnqk7k.cloudfront.net/wp-content/uploads/2017/09/28134754/mistakes-cooking-edibles-1024x640.jpg",
                "https://static01.nyt.com/images/2018/03/01/dining/01COOKING-CHICKEN-CURRY1/01COOKING-CHICKEN-CURRY1-threeByTwoMediumAt2X.jpg",
                "https://cdn.apartmenttherapy.info/image/fetch/f_auto,q_auto:eco/https://storage.googleapis.com/gen-atmedia/3/2012/08/45eaffc172bb14128eddce07ff31305a2d0fb560.jpeg"

        };
        ImageAdapter imageAdapter = new ImageAdapter(imageSet);
        imagesList.setAdapter(imageAdapter);
    }





    private static class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
        String[] mDataset;

        private static class ImageViewHolder extends RecyclerView.ViewHolder {
            ImageView mImageView;

            public ImageViewHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView;
            }
        }

        public ImageAdapter(String[] dataset) {
            mDataset = dataset;
        }

        @NonNull
        @Override
        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // create a new view
            ImageView v = (ImageView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.image_item, parent, false);
            return new ImageViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
            Glide.with(holder.mImageView)
                    .load(mDataset[position])
                    .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(32)))
                    .into(holder.mImageView);
        }

        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }






    private class Ingredient {
        String name;
        String quantity;

        Ingredient(String name, String quantity) {
            this.name = name;
            this.quantity = quantity;
        }
    }

    private static class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
        private Ingredient[] mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        static class IngredientViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            TextView ingredientName;
            TextView ingredientQuantity;

            IngredientViewHolder(RelativeLayout ingredient) {
                super(ingredient);
                ingredientName = ingredient.findViewById(R.id.ingredientName);
                ingredientQuantity = ingredient.findViewById(R.id.ingredientQuantity);

            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        IngredientAdapter(Ingredient[] myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @NonNull
        @Override
        public IngredientAdapter.IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                         int viewType) {
            // create a new view
            RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ingredient_item, parent, false);
            return new IngredientViewHolder(v);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.ingredientName.setText(mDataset[position].name);
            holder.ingredientQuantity.setText(mDataset[position].quantity);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}
