package com.githukudenis.takehomeassignment.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.githukudenis.takehomeassignment.presentation.routes.signin.SignInRoute
import com.githukudenis.takehomeassignment.presentation.routes.signup.SignUpRoute

@Composable

fun TakeHomeNavHost(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = TakeHomeDestination.SignIn.route) {
        composable(route = TakeHomeDestination.SignIn.route) {
            SignInRoute(onSignUpInstead = {
                navController.navigate(
                    route = TakeHomeDestination.SignUp.route
                ) {
                    popUpTo(route = TakeHomeDestination.SignIn.route)
                }
            })
        }
        composable(route = TakeHomeDestination.SignUp.route) {
            SignUpRoute(onSignInInstead = {
                navController.popBackStack()

            })
        }
    }
}

sealed class TakeHomeDestination(val route: String) {
    data object SignIn : TakeHomeDestination("sign_in")
    data object SignUp : TakeHomeDestination("sign_up")
}