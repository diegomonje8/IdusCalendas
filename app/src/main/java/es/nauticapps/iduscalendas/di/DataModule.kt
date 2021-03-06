package es.nauticapps.iduscalendas.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.nauticapps.iduscalendas.data.config.IdusNetwork
import es.nauticapps.iduscalendas.data.config.IdusRetrofit
import es.nauticapps.iduscalendas.data.config.IdusService
import es.nauticapps.iduscalendas.data.config.NetworkManager
import es.nauticapps.iduscalendas.data.idus.repository.IdusRepositoryImpl
import es.nauticapps.iduscalendas.data.idus.repository.remote.IdusRemoteRepositoryImpl
import es.nauticapps.iduscalendas.domain.IdusRepository
import retrofit2.Retrofit
import retrofit2.create


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    //@Provides
    //fun providesIdusRetrofit(): IdusRetrofit = IdusRetrofit()

    //@Provides
    //fun providesRetrofit(idusRetrofit: IdusRetrofit) : Retrofit = idusRetrofit.loadRetrofit()

    //@Provides
    //fun providesIdusService(retrofit: Retrofit) : IdusService = retrofit.create(IdusService::class.java)

    //@Provides
    //fun provideIdusNetwork(service: IdusService): IdusNetwork = IdusNetwork(service)

    //@Provides
    //fun provideIdusRemoteRepositoryImpl(network: IdusNetwork) : IdusRemoteRepositoryImpl = IdusRemoteRepositoryImpl(network)

    //@Provides
    //fun provideIdusRepositoryImpl(remote: IdusRemoteRepositoryImpl) : IdusRepositoryImpl = IdusRepositoryImpl(remote)

    //@Provides
    //fun provideIdusRepository(remote: IdusRemoteRepositoryImpl) :  IdusRepository = IdusRepositoryImpl(remote)

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    fun provideIdusRepository(context: Context) : IdusRepository = IdusRepositoryImpl(
        NetworkManager(context),
        IdusRemoteRepositoryImpl(IdusNetwork(IdusRetrofit().loadRetrofit().create(IdusService::class.java)))
    )

}