package org.netzd.procesosdip;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;


import org.netzd.procesosdip.webservices.Entity;
import org.netzd.procesosdip.webservices.JSONParser;
import org.netzd.procesosdip.webservices.Petition;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */

/*
public class MainActivityFragment extends Fragment {


    private RecyclerView moviesRecyclerView = null;
    private List<Video> videos = null;
    private ProgressBar moviesProgressBar = null;
    private JSONParser jsonParser = null;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moviesRecyclerView = (RecyclerView) view.findViewById(R.id.moviesRecyclerView);
        moviesProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    }

    private class  ObtenerInformacionTask extends AsyncTask<Void, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {
            try{
                JSONParser jsonParser = new JSONParser();
                videos = jsonParser.getVideos("http://www.omdbapi.com/?s=superman&apikey=2b28d307&r=json", new Petition(Entity.NONE));
                if(videos!=null && !videos.isEmpty()){
                    return true;
                }else{
                    return false;
                }
            }catch (Exception e){
                return false;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            moviesProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            moviesProgressBar.setVisibility(View.GONE);
            if(aBoolean){
                setLista();
            }else{
                Toast.makeText(getActivity(), "Hubo un error", Toast.LENGTH_LONG).show();
            }
        }

        private void obtenerInformacion{
            if(NetworkConnection.isConnectionAvailable(getContext())) {
                ObtenerInformacionTask obtenerInformacionTask = new ObtenerInformacionTask();
                obtenerInformacionTask.execute();
            }else {
                Toast.makeText(getActivity(), "No hay conexioc", Toast.LENGTH_LONG).show();
            }
        }

        private void setLista(){
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
            moviesRecyclerView.setHasFixedSize(true);
            moviesRecyclerView.setLayoutManager(layoutManager);
            VideoAdapter videoAdapter = new VideoAdapter(videos,getActivity());
            moviesRecyclerView.setAdapter(videoAdapter);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onCancelled(Boolean aBoolean) {
            super.onCancelled(aBoolean);
        }
    }
}*/

public class MainActivityFragment extends Fragment {

    private RecyclerView moviesRecyclerView = null;
    private List<Video> videos=null;

    private ProgressBar moviesProgressBar=null;
    private JSONParser jsonParser=null;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moviesRecyclerView =(RecyclerView) view.findViewById(R.id.moviesRecyclerView);
        moviesProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        obtenerInformacion();
    }

    private void obtenerInformacion(){
        if(NetworkConnection.isConnectionAvailable(getContext())){
            ObtenerInformacionTask obtenerInformacionTask = new ObtenerInformacionTask();
            obtenerInformacionTask.execute("http://www.omdbapi.com/?s=superman&apikey=2b28d307&r=json");
        }else{
            Toast.makeText(getContext(), "No hay conexion",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void setLista(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false);
        moviesRecyclerView.setHasFixedSize(true);
        moviesRecyclerView.setLayoutManager(layoutManager);
        VideoAdapter videoAdapter = new VideoAdapter(videos,getActivity());
        moviesRecyclerView.setAdapter(videoAdapter);
    }

    private class ObtenerInformacionTask extends
            AsyncTask<String,Integer,Boolean>{

        @Override
        protected Boolean doInBackground(String... voids) {
            try{
                JSONParser jsonParser = new JSONParser();
                videos=jsonParser.getVideos(voids[0],
                        new Petition(Entity.NONE));
                if(videos!=null && !videos.isEmpty()){
                    return true;
                }else{
                    return false;
                }
            }catch (Exception e){
                return false;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            moviesProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            moviesProgressBar.setVisibility(View.GONE);
            if(aBoolean){
                setLista();
            }else{
                Toast.makeText(getActivity(), "Hubo un error",
                        Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            moviesProgressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Se cancelo",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled(Boolean aBoolean) {
            super.onCancelled(aBoolean);
        }
    }
}



