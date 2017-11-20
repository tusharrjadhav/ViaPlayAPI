package com.viaplayapi.sample.viewmodel;

import com.viaplayapi.sample.data.Section;
import com.viaplayapi.sample.data.source.local.LocalRepository;
import com.viaplayapi.sample.data.source.remote.RemoteRepository;
import com.viaplayapi.sample.viewmodel.PageViewModel.GetPageCallback;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import retrofit2.Call;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Tushar_temp on 20/11/17
 */
public class PageViewModelTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private LocalRepository mockLocalRepository;

    @Mock
    private RemoteRepository mockRemoteRepository;

    private PageViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        viewModel = new PageViewModel(mockRemoteRepository, mockLocalRepository);
    }

    @Test
    public void shouldCallRemoteRootPage() throws Exception {

        Call mockCall = mock(Call.class);
        when(mockRemoteRepository.getRootPage()).thenReturn(mockCall);

        GetPageCallback mockGetPageCallback = mock(GetPageCallback.class);

        viewModel.getRootFromService(mockGetPageCallback);

        verify(mockRemoteRepository).getRootPage();
    }

    @Test
    public void shouldCallRemoteSctionPage() throws Exception {
        String testUrl = "TestUrl";
        Call mockCall = mock(Call.class);
        when(mockRemoteRepository.getSectionPage(eq(testUrl))).thenReturn(mockCall);

        GetPageCallback mockGetPageCallback = mock(GetPageCallback.class);

        Section mockSection = mock(Section.class);
        when(mockSection.getHref()).thenReturn(testUrl);
        viewModel.getSectionPageFromService(mockSection, mockGetPageCallback);

        verify(mockRemoteRepository).getSectionPage(eq(testUrl));
    }

    @Test
    public void shouldCallLocalSectionPage() throws Exception {
        String sectionId = "Sectionid";
        GetPageCallback mockCallback = mock(GetPageCallback.class);
        viewModel.getSectionPage(sectionId, mockCallback);
        verify(mockLocalRepository).getPageById(eq(sectionId), eq(mockCallback));
    }

    @Test
    public void shouldCallLocalRootPage() throws Exception {
        GetPageCallback mockCallback = mock(GetPageCallback.class);
        viewModel.getRootPage(mockCallback);
        verify(mockLocalRepository).getRootPage(eq(mockCallback));
    }
}