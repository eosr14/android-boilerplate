package com.github.fj.android.awesomeapp.uc.photo

import com.github.fj.android.awesomeapp.model.photo.ImageDetail
import com.github.fj.android.awesomeapp.repository.photo.PhotoRepository
import com.github.fj.android.rx.flatMapInner
import io.reactivex.Single
import timber.log.Timber

/**
 * @author Francesco Jo(nimbusob@gmail.com)
 * @since 12 - Nov - 2018
 */
internal class PhotoUseCaseImpl(
    private val repo: PhotoRepository
) : PhotoUseCase {
    override fun load(strict: Boolean, forceRefresh: Boolean): Single<List<ImageDetail>> {
        val list = if (strict) {
            repo.loadList(forceRefresh)
        } else {
            if (forceRefresh) {
                repo.loadList(true)
                    .onErrorResumeNext {
                        Timber.d(it)
                        return@onErrorResumeNext repo.loadList(false)
                    }
            } else {
                repo.loadList(false)
            }
        }

        return list.flatMapInner {
            ImageDetail.from(it)
        }
    }
}
