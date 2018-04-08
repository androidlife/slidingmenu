package com.laaptu.sliding.screen.home.gallery

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.laaptu.sliding.R
import com.laaptu.sliding.model.Story
import com.laaptu.sliding.screen.home.DEAL_DATA
import kotlinx.android.synthetic.main.fragment_page.*

class StoryFragment : Fragment() {
    companion object {
        fun getInstance(story: Story): StoryFragment {
            val pagerFragment = StoryFragment()
            val params = Bundle()
            params.putParcelable(DEAL_DATA, story)
            pagerFragment.arguments = params
            return pagerFragment
        }

        fun getDeal(params: Bundle?): Story? {
            if (params != null && params.containsKey(DEAL_DATA))
                return params.getParcelable(DEAL_DATA)
            return null
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val deal = getDeal(arguments) ?: return
        ivDeal.setImageResource(deal.imgResId)
    }
}