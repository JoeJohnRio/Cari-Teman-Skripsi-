package com.example.cariteman.ui

//package com.example.cariteman
//
//import androidx.recyclerview.widget.RecyclerView
//
//class DashboardItemAdapter(var users: ArrayList<User>) :
//    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {
//    fun updateUsers(newUsers: List<User>) {
//        users.clear()
//        users.addAll(newUsers)
//        notifyDataSetChanged()
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = UserViewHolder(
//        LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
//    )
//    override fun getItemCount() = users.size
//    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        holder.bindHistory(users[position])
//    }
//    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        private val imageView = view.imageView
//        private val userName = view.name
//        private val userEmail = view.email
//        fun bindHistory(country: User) {
//            userName.text = country.firstName + " " + country.lastName
//            userEmail.text = country.email
//            imageView.loadImage(country.avatar)
//        }
//    }
//}